package com.yinkaolu.rbcweatherapp.data.api.model

import com.google.gson.annotations.SerializedName
import com.yinkaolu.rbcweatherapp.data.api.model.*

/**
 * Represents the response to the Get Weather Endpoint
 * https://openweathermap.org/current#geo
 */
data class WeatherReport(
    @SerializedName("id")
    val cityID: String? = null,
    @SerializedName("name")
    val cityName: String? = null,
    @SerializedName("dt")
    val requestedTime: Double? = null,
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
    @SerializedName("weather")
    val weather: List<Weather> = emptyList(),
    @SerializedName("main")
    val mainWeather: MainWeather? = null,
    @SerializedName("visibility")
    val visibility: Double? = null,
    @SerializedName("wind")
    val windReport: WindReport? = null,
    @SerializedName("rain")
    val rainReport: RainReport? = null,
    @SerializedName("snow")
    val snowReport: SnowReport? = null,
    @SerializedName("clouds")
    val cloudReport: CloudReport? = null
)
