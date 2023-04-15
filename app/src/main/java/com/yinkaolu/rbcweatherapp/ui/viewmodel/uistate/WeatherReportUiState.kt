package com.yinkaolu.rbcweatherapp.ui.viewmodel.uistate

import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForcastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

/**
 * Represents the states of the presented UI based on state of ViewModel
 * and date available
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
        val forcastSummary: ForcastSummary
    ) : WeatherReportUiState

    object Loading: WeatherReportUiState
}