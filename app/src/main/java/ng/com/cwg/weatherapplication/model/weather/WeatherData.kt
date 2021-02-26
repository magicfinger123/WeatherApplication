package ng.com.cwg.weatherapplication.model.weather

import com.google.gson.annotations.SerializedName


class WeatherData {

    var dt: Long? = null
    var main: Main? = null
    var weather: List<Weather>? = null
    var clouds: Clouds? = null
    var wind: Wind? = null
    var visibility: Int? = null
    var pop: Double? = null
    var sys: Sys? = null
    @SerializedName("dt_txt")
    var dtTxt: String? = null
}