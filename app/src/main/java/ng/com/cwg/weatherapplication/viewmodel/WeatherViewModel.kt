package ng.com.cwg.weatherapplication.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfDto
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity
import ng.com.cwg.weatherapplication.db.io.SaveWeatherAppDb
import ng.com.cwg.weatherapplication.model.location.LocationModel
import ng.com.cwg.weatherapplication.model.weather.WeatherCurrentInfo
import ng.com.cwg.weatherapplication.model.weather.WeatherDto
import ng.com.cwg.weatherapplication.model.weather.WeatherForcastDto
import ng.com.cwg.weatherapplication.service.WeatherResource
import ng.com.cwg.weatherapplication.service.WeatherService
import ng.com.cwg.weatherapplication.utils.AppUtils

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 23/02/2021 17:48
 */
class WeatherViewModel(application: Application) : AndroidViewModel(application){
    val service =  WeatherService()
    private var locationRequest: LocationRequest? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var weatherCurrentInfo:MutableLiveData<WeatherCurrentInfo> = MutableLiveData<WeatherCurrentInfo>()

    fun observeCurrentWeatherInfo():LiveData<WeatherCurrentInfo>{
        return weatherCurrentInfo
    }
    fun setWeatherCurrentInfo(data: WeatherCurrentInfo){
        weatherCurrentInfo.value = data
    }

    private val saveWeatherAppDb = Room.databaseBuilder(
        getApplication(),
        SaveWeatherAppDb::class.java,
        "savedWeatherInfo"
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    fun observeInfo(lat: String, lon: String, apiKey: String): LiveData<WeatherResource<WeatherDto>>{
        return service.getCurrentLocationReport(lat, lon, apiKey)
    }
    fun observeForecast(lat: String, lon: String, apiKey: String): LiveData<WeatherResource<WeatherForcastDto>>{
        return service.getWeatherForecastInfo(lat, lon, apiKey)
    }
    @SuppressLint("MissingPermission")
    fun observeLocation(fusedLocationClient: FusedLocationProviderClient):LiveData<LocationModel>{
        this.fusedLocationClient = fusedLocationClient
        var locationLiveData = MutableLiveData<LocationModel>()
        locationRequest = LocationRequest.create()
        locationRequest!!.priority =  LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest!!.interval = (20 * 1000).toLong()
        var locationCallback:LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult !== null) {
                    locationResult.locations.forEach{
                        if(it == null) {
                            AppUtils.debug("locations details null")
                        }
                        else {
                            var locationModel = LocationModel()
                            locationModel.latitude = it.latitude
                            locationModel.longitude = it.longitude
                            //observeInfo(locationModel.latitude,locationModel.longitude)
                            locationLiveData.value = locationModel
                            AppUtils.debug("locations details $it")
                        }
                        println("current location info: $it")
                    }
                }

            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest!!, locationCallback, null)
        return locationLiveData

/*
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                if(it == null) {
                    AppUtils.debug("locations details null")
                }
                else {
                    var locationModel = LocationModel()
                    locationModel.latitude = it.latitude
                    locationModel.longitude = it.longitude
                    //observeInfo(locationModel.latitude,locationModel.longitude)
                    locationLiveData.value = locationModel
                    AppUtils.debug("locations details $it")
                }
                println("current location info: $it")
            }
        return locationLiveData*/
    }
    fun saveWeatherInfo(savedWeatherDto: SaveWeatherInfDto){
       saveWeatherAppDb.favoritesDao.addToFavorites(
           SaveWeatherInfoEntity(
               0,
               savedWeatherDto.latitude,
               savedWeatherDto.longitude,
               savedWeatherDto.weatherType,
               savedWeatherDto.averageTemperature,
               savedWeatherDto.date,
               savedWeatherDto.address,
               savedWeatherDto.title
           )
       )
    }
}