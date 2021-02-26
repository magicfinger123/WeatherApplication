package ng.com.cwg.weatherapplication.service;

import io.reactivex.Flowable;
import ng.com.cwg.weatherapplication.model.weather.WeatherDto;
import ng.com.cwg.weatherapplication.model.weather.WeatherForcastDto;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 30/09/2020 15:43
 */
public interface WebInterface {
   // String CURRENT_WEATHER_INFO = "/data/2.5/weather?q=Agbara&appid=d650e5fbff84aeb089e3d3177bfa2249"; lat={lat}&lon={lon}
    String CURRENT_WEATHER_INFO = "/data/2.5/weather";
    String FORCAST_WEATHER_INFO = "data/2.5/forecast?q=Agbara&appid=d650e5fbff84aeb089e3d3177bfa2249";

    @GET(CURRENT_WEATHER_INFO)
    Flowable<WeatherDto> getCurrentWeatherInfo(@Query("lat") String lat,@Query("lon") String lon, @Query("appid") String appId);

    @GET(FORCAST_WEATHER_INFO)
    Flowable<WeatherForcastDto> getWeatherForecastInfo(@Query("lat") String lat,@Query("lon") String lon, @Query("appid") String appId);;



}