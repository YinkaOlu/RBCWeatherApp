package com.yinkaolu.rbcweatherapp.data.api.weather

import com.google.gson.annotations.SerializedName

data class RainReport(
    @SerializedName("1h")
    val rainOverLastHour: Double? = null,
    @SerializedName("3h")
    val rainOverLastThreeHour: Double? = null
)