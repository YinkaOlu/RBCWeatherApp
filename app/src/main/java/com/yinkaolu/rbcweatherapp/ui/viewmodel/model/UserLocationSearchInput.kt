package com.yinkaolu.rbcweatherapp.ui.viewmodel.model

/**
 * Represents Location input from the user to search against
 */
data class UserLocationSearchInput(
    val cityName: String,
    val state: String? = null,
    val country: String? = null
)