package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class WindReport(
    @SerializedName("speed")
    val windSpeed: Double? = null,
    @SerializedName("gust")
    val windGust: Double? = null,
    @SerializedName("deg")
    val windDirectionDegree: Double? = null,
)