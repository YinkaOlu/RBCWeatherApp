package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

data class WindReport(
    @SerializedName("speed")
    val windSpeed: Double? = null,
    @SerializedName("gust")
    val windGust: Double? = null,
    @SerializedName("deg")
    val windDirectionDegree: Double? = null,
)