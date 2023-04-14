package com.yinkaolu.rbcweatherapp.data.api

interface WeatherService {
    suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ) : WeatherServiceResponse
}