package com.yinkaolu.rbcweatherapp.data.api

import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

class RealWeatherService : WeatherService {
    private var weatherAPIService: WeatherAPI
    private val apiKey = "e1446afca0b722b83a6a5ffeaae20838"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherAPIService = retrofit.create(WeatherAPI::class.java)
    }

    override suspend fun retrieveCurrentWeather(
        latitude: String,
        longitude: String
    ): WeatherServiceResponse {
        val response = weatherAPIService.getWeatherDetails(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        ).execute()

        return response.body()?.let {
            if (response.isSuccessful) {
                WeatherServiceResponse.Success(it)
            } else {
                WeatherServiceResponse.Error
            }
        } ?: WeatherServiceResponse.Error
    }
}

sealed interface WeatherServiceResponse {
    data class Success(
        val details: WeatherReport
    ) : WeatherServiceResponse

    object Error: WeatherServiceResponse
}