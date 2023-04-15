package com.yinkaolu.rbcweatherapp.ui.viewmodel.model

/**
 * Represents information to be shown in location section
 */
data class LocationSummary(
    val cityName: String,
    val stateName: String? = null,
    val countryName: String? = null
)
