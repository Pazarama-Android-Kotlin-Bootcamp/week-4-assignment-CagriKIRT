package com.example.wheatherapp.models

import android.graphics.drawable.Drawable

data class WhetherModel(
    val day: String,
    var image: Drawable?,
    val degree1: Double,
    val degree2: Double,
)
