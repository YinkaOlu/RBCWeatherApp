package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case that represents the loading of Weather Information for a give GeoLocation.
 * Converts raw Weather report to a summarized version to be displayed
 */
class LoadWeatherReportUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    val openWeatherRepository: OpenWeatherRepository
) {

    suspend fun invoke(
        lastGeoLocation: GeoLocation
    ): SummarizedWeatherReport = withContext(dispatcher) {
        val weatherReport = openWeatherRepository.loadCurrentWeatherReport(
            longitude = "${lastGeoLocation.longitude}",
            latitude = "${lastGeoLocation.latitude}"
        )

        SummarizedWeatherReport(
            weatherSummary = weatherReport.toWeatherSummary(),
            locationSummary = lastGeoLocation.toLocationSummary()
        )
    }
}