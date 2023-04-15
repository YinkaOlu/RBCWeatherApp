package com.yinkaolu.rbcweatherapp.data.api.model.weather

/**
 * API model representing the summarized weather information for a location
 */
data class Weather(
    val id: String? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)
