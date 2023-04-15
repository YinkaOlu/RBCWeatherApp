package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForcastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport

interface OpenWeatherRepository {
    @Throws(Exception::class)
    suspend fun loadForcastReport(
        latitude: String,
        longitude: String
    ) : ForcastReport
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