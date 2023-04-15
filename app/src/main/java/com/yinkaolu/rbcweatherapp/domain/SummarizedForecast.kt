package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForecastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary

data class SummarizedForecast(
    val locationSummary: LocationSummary?,
    val forecastSummary: ForecastSummary
)
