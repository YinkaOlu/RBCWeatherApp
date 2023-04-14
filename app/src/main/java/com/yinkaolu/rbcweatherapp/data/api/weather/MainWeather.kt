package com.yinkaolu.rbcweatherapp.data.api.weather

import com.google.gson.annotations.SerializedName

data class MainWeather(
    @SerializedName("temp")
    val temperature: Double? = null,
    @SerializedName("feels_like")
    val feelsLikeTemperature: Double? = null,
    @SerializedName("pressure")
    val airPressure: Double? = null,
    @SerializedName("humidity")
    val humidity: Double? = null,
    @SerializedName("temp_max")
    val maxTemperature: Double? = null,
    @SerializedName("temp_min")
    val minTemperature: Double? = null,
    @SerializedName("sea_level")
    val seaLevel: Double? = null,
    @SerializedName("grnd_level")
    val groundLevel: Double? = null,
)