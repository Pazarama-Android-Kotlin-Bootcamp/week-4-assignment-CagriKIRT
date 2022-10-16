package com.example.wheatherapp.models.services

import com.example.wheatherapp.models.api_model.WhetherAPIModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiInterface {

    @GET("weather?lat=44.34&lon=10.99&")
    fun getMovies(/*@Query("appid") key: String*/): Call<WhetherAPIModel>

    companion object {

        var BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(apiKey: String): ApiInterface {

            val client = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor(apiKey))
                .build()


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).client(client)
                .build()


            return retrofit.create(ApiInterface::class.java)

        }
    }
}

/*
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "8ddadecc7ae4f56fee73b2b405a63659")
                .addHeader("appid", "8ddadecc7ae4f56fee73b2b405a63659")
                .build()
        )
    }
}
*/

class HeaderInterceptor(var apiKey:String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
/*"8ddadecc7ae4f56fee73b2b405a63659"*/
        request.url("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=${apiKey}")
        val response = chain.proceed(request.build())
        return response
    }

}

