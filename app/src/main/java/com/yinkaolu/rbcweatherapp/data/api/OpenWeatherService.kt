package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.geo.GeoLocationList
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport

interface OpenWeatherService {
    suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ) : OpenWeatherResponse<WeatherReport>

    suspend fun findGeoLocations(
        city: String,
        state: String? = null,
        country: String? = null
    ) : OpenWeatherResponse<GeoLocationList>
}