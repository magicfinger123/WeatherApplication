package ng.com.cwg.weatherapplication.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://api.openweathermap.org/"

    val service: WebInterface
        get() {
            val authorizationInterceptor = Interceptor { chain ->
                val requestBuilder: Request.Builder
                val original = chain.request()
                requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            retrofit =  getRetrofitBuilder()
                .client(buildHttpClient(authorizationInterceptor))
                .build()
            return  retrofit!!.create(WebInterface::class.java)
        }

    private fun buildHttpClient(authorizationInterceptor: Interceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(authorizationInterceptor)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }

    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY
            return logging
        }

    private fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }
}