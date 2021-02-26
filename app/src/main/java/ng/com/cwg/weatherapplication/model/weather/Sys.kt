package ng.com.cwg.weatherapplication.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null
    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null
}