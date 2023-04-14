package com.yinkaolu.rbcweatherapp.ui.viewmodel


sealed interface WeatherReportUiState {
    data class Error(
        val message: String
    ): WeatherReportUiState

    data class Ready(
        val detail: WeatherSummary
    ) : WeatherReportUiState

    object Loading: WeatherReportUiState
}