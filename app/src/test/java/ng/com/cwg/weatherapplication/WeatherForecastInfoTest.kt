package ng.com.cwg.weatherapplication

import android.app.Application
import android.location.Location
import android.location.LocationManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import ng.com.cwg.weatherapplication.model.weather.WeatherForcastDto
import ng.com.cwg.weatherapplication.service.WeatherResource
import ng.com.cwg.weatherapplication.viewmodel.WeatherViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.robolectric.RuntimeEnvironment.application


/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 24/02/2021 00:00
 */
class WeatherForecastInfoTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var application: Application = Mockito.mock(Application::class.java)

    private var latitude = "6.447809299999999"
    private var longitude =   "3.4723495"
    private var apikey = "d650e5fbff84aeb089e3d3177bfa2249"



    private val locationClient = mockk<FusedLocationProviderClient>(relaxed = true)

    //var locationGetter: LocationGetter? = null

    private lateinit var weatherViewModel: WeatherViewModel

    @Test
    fun getCurrentWeatherTest (){
        getWeatherInformationForCurrentLocation()
       getWeatherForCast()
        //getDeviceLocation()
    }

    private fun getWeatherForCast() {
        var id: Int? = null
        val observer = Observer<WeatherResource<WeatherForcastDto>> {
            TestCase.assertNotNull(it)
            when (it?.status) {
                WeatherResource.ResponseStatus.LOADING -> {
                    println("App is loading")
                }
                WeatherResource.ResponseStatus.SUCCESSFUL -> {
                    id = it.data!!.cod
                    assertTrue(it.status == WeatherResource.ResponseStatus.SUCCESSFUL)
                    TestCase.assertNotNull(id)
                }
                WeatherResource.ResponseStatus.ERROR -> {
                    println("subscribeToLoginObserver:Error ${it.data!!.message}")
                }
                WeatherResource.ResponseStatus.FAILED -> {
                    println("On Failure")
                }
            }
            println("id value:  $id")
        }
        try {
            weatherViewModel.observeForecast(latitude,longitude,apikey).observeForever(observer)
        } finally {
            weatherViewModel.observeForecast(latitude,longitude,apikey).removeObserver(observer)
        }
    }
    private fun getWeatherInformationForCurrentLocation() {
        weatherViewModel = WeatherViewModel(application)
    }
    private fun getDeviceLocation(){
        val location = Location(LocationManager.NETWORK_PROVIDER)
        location.latitude = 37.422
        location.longitude = -122.084
        location.accuracy = 3.0f
        locationClient.setMockMode(true)
        locationClient.setMockLocation(location)
        weatherViewModel.observeLocation(locationClient).observeForever {
            println(it.latitude)
            assertNotNull(it)
        }
    }
}