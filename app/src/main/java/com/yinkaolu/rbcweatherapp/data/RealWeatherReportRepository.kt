package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.RemoteWeatherService
import com.yinkaolu.rbcweatherapp.data.api.WeatherServiceResponse
import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport

class RealWeatherReportRepository(
    val remoteWeatherService: RemoteWeatherService
) : WeatherReportRepository {

    override suspend fun loadCurrentWeatherReport(
        latitude: String,
        longitude: String
    ): WeatherReport {
        val loadCurrentWeatherReportResponse = remoteWeatherService.retrieveCurrentWeather(
            latitude = latitude,
            longitude = longitude
        )

        return when(loadCurrentWeatherReportResponse) {
            is WeatherServiceResponse.Success<WeatherReport> -> {
                loadCurrentWeatherReportResponse.details
            }
            is WeatherServiceResponse.Error -> {
                // TODO: Handle network error
                throw java.lang.Exception("Network error")
            }
        }
    }
}