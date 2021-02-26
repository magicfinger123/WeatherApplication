package ng.com.cwg.weatherapplication.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.Place.*
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.fragment_maps.*
import ng.com.cwg.weatherapplication.viewmodel.FavoritesViewModel
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity
import ng.com.cwg.weatherapplication.utils.AppUtils
import ng.com.cwg.weatherapplication.utils.LocationUtil
import ng.com.cwg.weatherapplication.view.MainActivity

class MapsFragment : Fragment() {
    private lateinit var viewModel: FavoritesViewModel

    private lateinit var saveWeatherInfoEntity: List<SaveWeatherInfoEntity>
    private val callback = OnMapReadyCallback { googleMap ->
        if (saveWeatherInfoEntity.isEmpty())
            return@OnMapReadyCallback
        var  markers :  MutableList<MarkerOptions>  =  ArrayList()
        saveWeatherInfoEntity.forEach{
            val latlng = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
            val address = LocationUtil.getCompleteAddressString(it.latitude.toDouble(),it.longitude.toDouble(),context)
            val marker = MarkerOptions().position(latlng)
                .icon(AppUtils.bitmapDescriptorFromVector(context, R.drawable.ic_location_pickup))
                .title(it.averageTemperature).snippet(address)
            googleMap.addMarker(marker).showInfoWindow()
            markers.add(marker)
        }
        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            builder.include(marker.position)
        }
        val bounds = builder.build()
        val padding = 20 // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        googleMap.animateCamera(cu)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewListRv.setOnClickListener{
            (activity as MainActivity).viewFavorites()
            //searchPlaces()
        }
    }
    private fun init(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        viewModel.getFavorites()
        viewModel.observeFavorites().observe(viewLifecycleOwner, {
            saveWeatherInfoEntity = it
            mapFragment?.getMapAsync(callback)
        })
    }


}