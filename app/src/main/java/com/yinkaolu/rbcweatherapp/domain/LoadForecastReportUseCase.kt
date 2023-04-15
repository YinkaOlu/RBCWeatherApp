package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.data.OpenWeatherRepository
import com.yinkaolu.rbcweatherapp.data.api.model.geo.GeoLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadForecastReportUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    val openWeatherRepository: OpenWeatherRepository
) {
    suspend fun invoke(
        lastGeoLocation: GeoLocation
    ): SummarizedForecast = withContext(dispatcher) {
        val forecastReport = openWeatherRepository.loadForecastReport(
            longitude = "${lastGeoLocation.longitude}",
            latitude = "${lastGeoLocation.latitude}"
        )
        SummarizedForecast(
            forecastSummary = forecastReport.toForecastSummary(),
            locationSummary = lastGeoLocation.toLocationSummary()
        )
    }
}