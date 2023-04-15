package com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate

import com.yinkaolu.rbcweatherapp.domain.SummarizedForecast
import com.yinkaolu.rbcweatherapp.domain.SummarizedWeatherReport

/**
 * Represents the state of the presented UI to user
 */
sealed interface WeatherReportUiState {

    object RequestCurrentLocation: WeatherReportUiState

    data class Error(
        val message: String
    ): WeatherReportUiState

    data class MainWeatherPage(
        val summarizedWeatherReport: SummarizedWeatherReport
    ) : WeatherReportUiState

    data class ForecastDetailPage(
        val summarizedForecast: SummarizedForecast
    ) : WeatherReportUiState

    object Loading: WeatherReportUiState
}