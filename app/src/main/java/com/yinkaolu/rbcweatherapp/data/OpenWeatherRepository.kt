package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport

interface OpenWeatherRepository {
    @Throws(Exception::class)
    suspend fun loadForecastReport(
        latitude: String,
        longitude: String
    ) : ForecastReport
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

    @Throws(Exception::class)
    suspend fun findLocationByCoordinates(
        longitude: String,
        latitude: String,
    ) : GeoLocationList
}