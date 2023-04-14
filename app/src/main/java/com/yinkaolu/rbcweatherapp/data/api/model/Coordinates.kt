package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat")
    val latitude: Double? = null,
    @SerializedName("lon")
    val longitude: Double? = null,
)