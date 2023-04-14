package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class SnowReport(
    @SerializedName("1h")
    val snowOverLastHour: Double? = null,
    @SerializedName("3h")
    val snowOverLastThreeHour: Double? = null
)