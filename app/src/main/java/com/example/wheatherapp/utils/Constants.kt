package com.example.wheatherapp.utils

object Constants {
    fun baseUrl(api_key: String): String =
        "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=$api_key";
}