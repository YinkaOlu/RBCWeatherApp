package com.yinkaolu.rbcweatherapp.data.api.model.geo

import com.google.gson.annotations.SerializedName

/**
 * API model representing the location information return from OpenWeather
 */
data class GeoLocation(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("lat")
    val latitude: Double? = null,
    @SerializedName("lon")
    val longitude: Double? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("state")
    val state: String? = null
)
