package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

/**
 * Summarized version of a Weather Report. Contains only to relevant information to display
 */
data class SummarizedWeatherReport(
    val locationSummary: LocationSummary?,
    val weatherSummary: WeatherSummary
)
