package ng.com.cwg.weatherapplication

import android.app.Application
import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.location.FusedLocationProviderClient
import io.mockk.mockk
import junit.framework.Assert
import junit.framework.Assert.assertNotNull
import ng.com.cwg.weatherapplication.viewmodel.WeatherViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.robolectric.RuntimeEnvironment.application

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 24/02/2021 06:57
 */

 class LocationTest{

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var application: Application = mock(Application::class.java)


    @Mock
    private lateinit var location: Location

    private lateinit var weatherViewModel: WeatherViewModel

    private val locationClient = mockk<FusedLocationProviderClient>(relaxed = true)

    //region calculateDistance
    @Test
    fun `given a valid store and a valid location  to calculateDistance should return the correct distance`() {
        weatherViewModel = WeatherViewModel(application)
        val mockLocation = mock(Location::class.java)
        val latitude = 37.422
        val longitude = -122.084
        given(mockLocation.latitude).willReturn(latitude)
        given(mockLocation.longitude).willReturn(longitude)
        locationClient.setMockLocation(mockLocation)
        weatherViewModel.observeLocation(locationClient).observeForever {
            println(it.latitude)
            assertNotNull(it)
        }
    }
 }