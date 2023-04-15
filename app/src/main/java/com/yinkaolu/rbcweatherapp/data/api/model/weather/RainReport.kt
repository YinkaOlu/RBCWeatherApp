package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model representing the rain information for a location
 */
data class RainReport(
    @SerializedName("1h")
    val rainOverLastHour: Double? = null,
    @SerializedName("3h")
    val rainOverLastThreeHour: Double? = null
)