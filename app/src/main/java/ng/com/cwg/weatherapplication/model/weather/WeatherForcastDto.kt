package ng.com.cwg.weatherapplication.model.weather


class WeatherForcastDto(
    var list: List<WeatherData>?,
    var city: City?,
    var cnt: Int?,
    var message: String?,
    var cod: Int?
)