package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport

interface OpenWeatherService {
    suspend fun retrieveForcast(
        latitude: String,
        longitude: String
    ): OpenWeatherResponse<ForecastReport>

    suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ): OpenWeatherResponse<WeatherReport>

    suspend fun findGeoLocations(
        city: String,
        state: String? = null,
        country: String? = null
    ): OpenWeatherResponse<GeoLocationList>

    suspend fun findGeoLocationsByCoordinate(
        latitude: String,
        longitude: String
    ): OpenWeatherResponse<GeoLocationList>
}