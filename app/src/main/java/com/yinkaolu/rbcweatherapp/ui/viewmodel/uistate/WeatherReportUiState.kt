package com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate

import com.yinkaolu.rbcweatherapp.data.api.model.weather.ForecastReport
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForecastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

/**
 * Represents the state of the presented UI to user
 */
sealed interface WeatherReportUiState {

    object RequestLocationPermission: WeatherReportUiState

    data class Error(
        val message: String
    ): WeatherReportUiState

    data class MainWeatherPage(
        val locationSummary: LocationSummary?,
        val weatherSummary: WeatherSummary
    ) : WeatherReportUiState

    data class ForcastDetailPage(
        val locationSummary: LocationSummary?,
        val forecastSummary: ForecastSummary
    ) : WeatherReportUiState

    object Loading: WeatherReportUiState
}