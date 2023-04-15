package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.composable.components.LocationEntry
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.UserLocationSearchInput

/**
 * UI component that renders the error screen
 */
@Composable
fun ErrorScreen(
    errorMessage: String?,
    onSearchLocation: (userLocationSearchInput: UserLocationSearchInput) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = errorMessage ?: stringResource(R.string.error_message_title))
        LocationEntry(
            onSearchLocation = onSearchLocation
        )
    }
}