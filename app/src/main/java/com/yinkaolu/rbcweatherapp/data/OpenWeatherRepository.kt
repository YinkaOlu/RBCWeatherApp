package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport

interface OpenWeatherRepository {
    @Throws(Exception::class)
    suspend fun loadCurrentWeatherReport(
        latitude: String,
        longitude: String
    ) : WeatherReport

    @Throws(Exception::class)
    suspend fun findLocation(
        city: String,
        state: String? = null,
        country: String? = null
    ) : GeoLocationList
}