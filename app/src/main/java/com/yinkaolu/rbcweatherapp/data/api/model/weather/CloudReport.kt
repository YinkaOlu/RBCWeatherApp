package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model represting the cloud information return by OpenWeather
 */
data class CloudReport(
    @SerializedName("all")
    val coverage: Double? = null
)