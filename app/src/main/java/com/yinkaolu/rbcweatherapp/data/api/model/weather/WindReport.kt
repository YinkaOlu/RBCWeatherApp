package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model representing the wind information for a location
 */
data class WindReport(
    @SerializedName("speed")
    val windSpeed: Double? = null,
    @SerializedName("gust")
    val windGust: Double? = null,
    @SerializedName("deg")
    val windDirectionDegree: Double? = null,
)