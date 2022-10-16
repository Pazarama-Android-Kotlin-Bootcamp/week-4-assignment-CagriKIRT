package com.example.wheatherapp.models.api_model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)