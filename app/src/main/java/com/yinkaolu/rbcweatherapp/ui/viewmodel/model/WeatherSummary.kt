package com.yinkaolu.rbcweatherapp.ui.viewmodel.model

/**
 * Represents summarized weather information to be show in UI
 */
data class WeatherSummary(
    val weatherDescription: String,
    val temperature: String,
    val icon: String,
    val dateString: String
)
