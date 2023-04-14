package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport

interface RemoteWeatherService {
    suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ) : WeatherServiceResponse<WeatherReport>
}