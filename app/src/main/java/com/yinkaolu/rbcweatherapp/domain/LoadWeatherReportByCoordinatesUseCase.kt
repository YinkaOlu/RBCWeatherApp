package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.weather.WeatherReport
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case that represents the loading of Weather Information
 * for a give Coordinate (longitude & latitude).
 * Converts raw Weather report to a summarized version to be displayed
 */
class LoadWeatherReportByCoordinatesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    val openWeatherRepository: OpenWeatherRepository
) {

    suspend fun invoke(
        latitude: Double,
        longitude: Double
    ): WeatherReport = withContext(dispatcher) {
        openWeatherRepository.loadCurrentWeatherReport(
            longitude = "$longitude",
            latitude = "$latitude"
        )
    }
}