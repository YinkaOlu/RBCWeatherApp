package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.composable.LocationEntry
import com.yinkaolu.rbcweatherapp.ui.viewmodel.LocationInfo

@Composable
fun ErrorScreen(
    onSearchLocation: (locationInfo: LocationInfo) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.error_message_title))
        LocationEntry(
            onSearchLocation = onSearchLocation
        )
    }
}