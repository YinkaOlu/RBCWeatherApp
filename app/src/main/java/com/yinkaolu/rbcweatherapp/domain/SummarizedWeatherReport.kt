package com.yinkaolu.rbcweatherapp.domain

import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

data class SummarizedWeatherReport(
    val locationSummary: LocationSummary?,
    val weatherSummary: WeatherSummary
)
