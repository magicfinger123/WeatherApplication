package ng.com.cwg.weatherapplication.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.add_fav.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.addToFavorite
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.adapters.ForecastRvAdapter
import ng.com.cwg.weatherapplication.custom.CustomAlertDialog
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfDto
import ng.com.cwg.weatherapplication.model.location.LocationModel
import ng.com.cwg.weatherapplication.model.weather.*
import ng.com.cwg.weatherapplication.service.WeatherResource
import ng.com.cwg.weatherapplication.utils.AppUtils
import ng.com.cwg.weatherapplication.utils.LocationUtil
import ng.com.cwg.weatherapplication.view.MainActivity
import ng.com.cwg.weatherapplication.viewmodel.WeatherViewModel

class SearchPlace : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var savedWeatherDto: SaveWeatherInfDto
    private lateinit var locationObserver: Observer<LocationModel>
    private lateinit var weatherForcastObseerver: Observer<WeatherResource<WeatherForcastDto>>
    private lateinit var weatherObseerver: Observer<WeatherResource<WeatherDto>>
    private lateinit var weatherCurrentInfoObserver: Observer<WeatherCurrentInfo>
    private lateinit var locationModel: LocationModel
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_place_fragment, container, false)
    }
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationModel = arguments?.let { SearchPlaceArgs.fromBundle(it).locationModel }!!
        weatherViewModel = WeatherViewModel(activity!!.application)
        placesClient = Places.createClient(requireContext())
        savedWeatherDto = SaveWeatherInfDto()
        savedWeatherDto.date = AppUtils.getDate()
        setWeatherInfo()
        initWeatherObserver()
        initForecastObserver()
        addToFav()
        initSearch()
        weatherCurrentInfoObserver = Observer<WeatherCurrentInfo>{
            if (it!=null) {
                init(it)
            }
        }
        weatherViewModel.observeCurrentWeatherInfo().observe(viewLifecycleOwner,weatherCurrentInfoObserver)
        init(locationModel.latitude.toString(),locationModel.longitude.toString(),resources.getString(R.string.api_key))
        getForecast(locationModel.latitude.toString(),locationModel.longitude.toString(),resources.getString(R.string.api_key))
    }


    private  fun setWeatherInfo(){
        savedWeatherDto.latitude = (locationModel.latitude.toString())
        savedWeatherDto.longitude = (locationModel.longitude.toString())
        val address =   LocationUtil.getCompleteAddressString(
            locationModel.latitude!!.toDouble(),
            locationModel.longitude!!.toDouble(),
            context
        )
        savedWeatherDto.address  = address
    }
    private fun initWeatherObserver(){
        weatherObseerver = Observer<WeatherResource<WeatherDto>>{
            when (it?.status) {
                WeatherResource.ResponseStatus.LOADING -> {
                    (activity as MainActivity).showLoading(true)
                }
                WeatherResource.ResponseStatus.SUCCESSFUL ->{
                    (activity as MainActivity).showLoading(false)
                    val weatherCurrentInfo = WeatherCurrentInfo(

                        AppUtils.convertKelvinToCelsius(it.data!!.main!!.temp).toString()+resources.getString(
                        R.string.celsius_symbol
                    ),
                        AppUtils.convertKelvinToCelsius(it.data.main!!.tempMax).toString()+resources.getString(
                            R.string.celsius_symbol
                        ),
                        AppUtils.convertKelvinToCelsius(it.data.main!!.tempMin).toString()+resources.getString(
                            R.string.celsius_symbol
                        ),
                        it.data.weather!![0].main!!,
                        savedWeatherDto.address
                    )
                    weatherViewModel.setWeatherCurrentInfo(weatherCurrentInfo)
                }
                WeatherResource.ResponseStatus.ERROR -> {
                    (activity as MainActivity).showLoading(false)
                    Log.d("TAG", "subscribeToLoginObserver:Error ${it.data!!.message}")
                    println("subscribeToLoginObserver:Error ${it.data.message}")
                }
                WeatherResource.ResponseStatus.FAILED -> {
                    (activity as MainActivity).showLoading(false)
                    Log.d("TAG", "getUserCurrentLocation: Failed")
                    println("On Failure")
                }
            }
        }
    }
    private fun initForecastObserver(){
        weatherForcastObseerver = Observer<WeatherResource<WeatherForcastDto>>{
            when (it?.status) {
                WeatherResource.ResponseStatus.LOADING -> {
                    (activity as MainActivity).showLoading(true)
                }
                WeatherResource.ResponseStatus.SUCCESSFUL ->{
                    (activity as MainActivity).showLoading(false)
                    val list   = it.data!!.list
                    getDayAndTempAverage(list,it.data.list?.get(0)!!.main!!.temp!!)
                }
                WeatherResource.ResponseStatus.ERROR -> {
                    (activity as MainActivity).showLoading(false)
                    Log.d("TAG", "subscribeToLoginObserver:Error ${it.data!!.message}")
                    println("subscribeToLoginObserver:Error ${it.data.message}")
                }
                WeatherResource.ResponseStatus.FAILED -> {
                    (activity as MainActivity).showLoading(false)
                    Log.d("TAG", "getUserCurrentLocation: Failed")
                    println("On Failure")
                }
            }
        }
    }
    private fun init(it: WeatherCurrentInfo) {
        savedWeatherDto.averageTemperature = it.currentTemp
        savedWeatherDto.weatherType = it.weatherType
        currentTempTextView.text = it.currentTemp
        minTempTextView.text = it.minTemp
        maxTempTextView.text = it.maxTemp
        temperatureTextView.text = it.currentTemp
        weatherStatusTextView.text = it.weatherType
        placeInfo.text = it.address
        when (it.weatherType.toLowerCase()) {
            "clouds" -> {
                topRelativeLayout.setBackgroundResource(R.drawable.forest_cloudy)
                parentLayout.setBackgroundColor(resources.getColor(R.color.colorCloudy))
            }
            "rain" -> {
                topRelativeLayout.setBackgroundResource(R.drawable.forest_rainy)
                parentLayout.setBackgroundColor(resources.getColor(R.color.colorRainy))
            }
            "clear" -> {
                topRelativeLayout.setBackgroundResource(R.drawable.forest_sunny)
                parentLayout.setBackgroundColor(resources.getColor(R.color.colorSunny))
            }
        }

    }
    private fun init(lat:String, lon:String, apiKey:String){
        weatherViewModel.observeInfo(lat,lon,apiKey).observe(viewLifecycleOwner, weatherObseerver)
    }
    private fun getForecast(lat:String, lon:String, apiKey:String){
        weatherViewModel.observeForecast(lat,lon,apiKey).observe(viewLifecycleOwner, weatherForcastObseerver)
    }
    private fun getDayAndTempAverage(list: List<WeatherData>?, firstTemperature:Double) {
        val dayTempList: ArrayList<DayAndTemperature> = ArrayList()
        var temp: Double = firstTemperature
        list!!.forEachIndexed { index, element ->
            if ((index + 1) % 8 == 0) {
                temp /= 8
                val day = AppUtils.convertDate(element.dt!!)
                val dTemp = DayAndTemperature(day, temp.toString(), element.weather?.get(0)?.main)
                dayTempList.add(dTemp)
                temp = 0.0
                temp += element.main!!.temp!!
                println("day: $day")
            } else {
                temp += element.main!!.temp!!
            }
        }
        initForecastRV(dayTempList)
    }

    private fun initForecastRV(dayTempList: ArrayList<DayAndTemperature>){
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter: ForecastRvAdapter = ForecastRvAdapter(requireContext(), dayTempList)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun addToFav(){
        addToFavorite.setOnClickListener{
            addFavorite()
        }
    }

    private fun addFavorite(){
        var customAlertDialog = CustomAlertDialog(context)
        customAlertDialog.init(
            R.layout.add_fav,
            { view ->
                var btnContinue = view.findViewById<Button>(R.id.addToFavorite)
                var editText = view.findViewById<EditText>(R.id.favTitle)
                btnContinue.setOnClickListener(View.OnClickListener {
                    // accViewModel.getSavingsAccounts()
                    if (TextUtils.isEmpty(editText.text)) {
                        favTitle.error = "Please enter  a title"
                        return@OnClickListener
                    }
                    savedWeatherDto.title = editText.text.toString()
                    weatherViewModel.saveWeatherInfo(savedWeatherDto)
                    customAlertDialog.forceClose()
                })
            },
            true,
            false
        )
    }
    private fun initSearch(){
        searchPlaces.setOnClickListener {
            searchPlaces()
        }
    }
    private fun searchPlaces(){
        val fields = listOf(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.ID)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(requireContext())
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        val latlng = place.latLng.toString().replace("lat/lng:(","").replace(")","").split(",")
                        val lat = latlng[0].replace("[^\\d.]".toRegex(), "")
                        val lng = latlng[1].replace("[^\\d.]".toRegex(), "")
                        locationModel =  LocationModel(lat.toDouble(),lng.toDouble())
                        init(locationModel.latitude.toString(),locationModel.longitude.toString(),resources.getString(R.string.api_key))
                        getForecast(locationModel.latitude.toString(),locationModel.longitude.toString(),resources.getString(R.string.api_key))
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        AppUtils.debug(status.statusMessage)
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}