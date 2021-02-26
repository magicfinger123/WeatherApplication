package ng.com.cwg.weatherapplication.service

import com.google.gson.Gson
import retrofit2.HttpException


/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 05/10/2020 15:21
 */
class ErrorConverter {
    companion object {
        fun getErrorResponse(e: Throwable): ErrorResponse? {
            return if (e is HttpException) {
                val body = e.response()!!.errorBody()
                val message: ErrorResponse = try {
                    Gson().fromJson(
                        body!!.charStream(),
                       ErrorResponse::class.java
                    )
                } catch (e: Exception) {
                    val message = ErrorResponse()
                    message.message = "Something went wrong"
                    message.cod = "555"
                    message

                }
                message
            } else {
                val u = ErrorResponse()
                u.cod="500"
                u.message = "could not connect"
                u
            }
        }
    }
}