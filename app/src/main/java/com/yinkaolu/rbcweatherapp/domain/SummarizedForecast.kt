package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForecastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary

/**
 * Summarized version of a Forecast Report. Contains only to relevant information to display
 */
data class SummarizedForecast(
    val locationSummary: LocationSummary?,
    val forecastSummary: ForecastSummary
)
