package com.yinkaolu.rbcweatherapp.data.api.model.weather

import com.google.gson.annotations.SerializedName

/**
 * API model representing the Forecast data return for future weather information
 */
data class Forcast(
    @SerializedName("dt")
    val time: Double? = null,
    @SerializedName("main")
    val mainWeather: MainWeather? = null,
    @SerializedName("weather")
    val weather: List<Weather> = emptyList(),
    @SerializedName("visibility")
    val visibility: Double? = null,
    @SerializedName("pop")
    val precipitationProbability: Double? = null,
    @SerializedName("wind")
    val windReport: WindReport? = null,
    @SerializedName("rain")
    val rainReport: RainReport? = null,
    @SerializedName("snow")
    val snowReport: SnowReport? = null,
    @SerializedName("clouds")
    val cloudReport: CloudReport? = null
)
