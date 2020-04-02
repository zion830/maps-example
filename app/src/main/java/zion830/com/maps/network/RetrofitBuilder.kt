package zion830.com.maps.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zion830.com.maps.BASE_URL
import java.util.concurrent.TimeUnit

/*
 * Created by yunji on 02/04/2020
 */
object RetrofitBuilder {
    private const val TIME_OUT_SEC = 5L

    val service: ServiceApi by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
            .build()

        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ServiceApi::class.java)
    }
}