package com.example.wheatherapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null


    fun getClient(api_key: String): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(Constants.baseUrl(api_key)).addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

        return retrofit as Retrofit
    }

}