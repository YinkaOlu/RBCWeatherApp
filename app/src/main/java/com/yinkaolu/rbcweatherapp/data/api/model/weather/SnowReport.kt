package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model representing the snow information for a location
 */
data class SnowReport(
    @SerializedName("1h")
    val snowOverLastHour: Double? = null,
    @SerializedName("3h")
    val snowOverLastThreeHour: Double? = null
)