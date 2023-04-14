package com.yinkaolu.rbcweatherapp.data

import com.yinkaolu.rbcweatherapp.data.api.model.WeatherReport
import kotlinx.coroutines.flow.Flow

interface WeatherReportRepository {
    @Throws(Exception::class)
    suspend fun loadCurrentWeatherReport(
        latitude: String,
        longitude: String
    ) : WeatherReport
}