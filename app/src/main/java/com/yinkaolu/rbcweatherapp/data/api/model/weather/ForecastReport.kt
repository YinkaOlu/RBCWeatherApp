package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model representing the full future forecast for a location
 */
data class ForecastReport(
    @SerializedName("cnt")
    val count: Int,
    @SerializedName("list")
    val list: List<Forcast>
)
