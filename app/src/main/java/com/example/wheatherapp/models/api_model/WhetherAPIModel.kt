package com.example.wheatherapp.models.api_model

data class WhetherAPIModel(
    val main: Main,
    val weather: List<Weather>
)