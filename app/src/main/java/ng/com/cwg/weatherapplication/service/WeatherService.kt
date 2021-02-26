package ng.com.cwg.weatherapplication.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import ng.com.cwg.weatherapplication.model.weather.WeatherDto
import ng.com.cwg.weatherapplication.model.weather.WeatherForcastDto

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 23/02/2021 21:26
 */
class WeatherService {
    private val mediatorLiveData = MediatorLiveData<WeatherResource<WeatherDto>>()
    private val weatherForecastLiveData = MediatorLiveData<WeatherResource<WeatherForcastDto>>()
    private var webInterface: WebInterface? = RetInstance.service
    fun getCurrentLocationReport(lat:String, lon:String, apiKey:String):MutableLiveData<WeatherResource<WeatherDto>>{
        mediatorLiveData.value = WeatherResource.loading(null as WeatherDto?)
        val source: LiveData<WeatherResource<WeatherDto>> = LiveDataReactiveStreams.fromPublisher(
            webInterface!!.getCurrentWeatherInfo(lat,lon, apiKey)
                .onErrorReturn { t -> val s =  ErrorConverter.getErrorResponse(t)
                    val r = WeatherDto()
                    r.cod = Integer.parseInt(s?.cod!!)
                    r.message = s.message
                    r }
                .map(object :
                    Function<WeatherDto, WeatherResource<WeatherDto>> {
                    override fun apply(t: WeatherDto): WeatherResource<WeatherDto>? {
                        if (t.cod != 200)
                            return WeatherResource.error(t.message!!,t)
                        return WeatherResource.successful(t)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
        mediatorLiveData.addSource(source) {
            mediatorLiveData.value = it
            mediatorLiveData.removeSource(source)
        }
        return mediatorLiveData
    }
    fun getWeatherForecastInfo(lat:String, lon:String, apiKey:String):MutableLiveData<WeatherResource<WeatherForcastDto>>{
        weatherForecastLiveData.value = WeatherResource.loading(null as WeatherForcastDto?)
        val source: LiveData<WeatherResource<WeatherForcastDto>> = LiveDataReactiveStreams.fromPublisher(
            webInterface!!.getWeatherForecastInfo(lat,lon, apiKey)
                .onErrorReturn { t -> val s =  ErrorConverter.getErrorResponse(t)
                    val r = WeatherForcastDto(null, null, null, null, null)
                    r.cod = Integer.parseInt(s?.cod!!)
                    r.message = s.message
                    r }
                .map(object :
                    Function<WeatherForcastDto, WeatherResource<WeatherForcastDto>> {
                    override fun apply(t: WeatherForcastDto): WeatherResource<WeatherForcastDto>? {
                        if (t.cod != 200)
                            return WeatherResource.error(t.message!!,t)
                        return WeatherResource.successful(t)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
        weatherForecastLiveData.addSource(source) {
            weatherForecastLiveData.value = it
            weatherForecastLiveData.removeSource(source)
        }
        return weatherForecastLiveData
    }

}