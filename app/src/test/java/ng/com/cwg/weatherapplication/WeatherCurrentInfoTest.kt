package ng.com.cwg.weatherapplication

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertNotNull
import ng.com.cwg.weatherapplication.model.weather.WeatherDto

import ng.com.cwg.weatherapplication.service.WeatherResource
import ng.com.cwg.weatherapplication.viewmodel.WeatherViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.robolectric.RuntimeEnvironment.application

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 23/02/2021 18:08
 */
class WeatherCurrentInfoTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule:TestRule = InstantTaskExecutorRule()

    private var application: Application = mock(Application::class.java)

    private lateinit var weatherViewModel: WeatherViewModel
    private var latitude = "6.447809299999999"
    private var longitude =   "3.4723495"
    private var apikey = "d650e5fbff84aeb089e3d3177bfa2249"

    @Test
    fun getCurrentWeatherTest (){
        getWeatherInformationForCurrentLocation()
        getUserCurrentLocation()
    }

    private fun getUserCurrentLocation() {
        var id: Int? = null
        val observer = Observer<WeatherResource<WeatherDto>> {
            assertNotNull(it)
            when (it?.status) {
                WeatherResource.ResponseStatus.LOADING -> {
                    println("App is loading")
                }
                WeatherResource.ResponseStatus.SUCCESSFUL -> {
                    id = it.data!!.id
                    println("App has loaded: $id")
                    assertNotNull(id)
                }
                WeatherResource.ResponseStatus.ERROR -> {
                    println("getWeatherForcast:Error ${it.data!!.message}")
                }
                WeatherResource.ResponseStatus.FAILED -> {
                    println("On Failure")
                }
            }
            println("id value:  $id")
        }
        try {
            weatherViewModel.observeInfo(latitude, longitude, apikey).observeForever(observer)
        } finally {
            weatherViewModel.observeInfo(latitude, longitude, apikey).removeObserver(observer)
        }
    }
    private fun getWeatherInformationForCurrentLocation() {
      weatherViewModel = WeatherViewModel(application)
        //weatherViewModel.observeInfo()
    }
}