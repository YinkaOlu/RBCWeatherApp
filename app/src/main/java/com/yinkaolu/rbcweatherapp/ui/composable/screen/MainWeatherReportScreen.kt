package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.data.api.weather.WeatherReport
import com.yinkaolu.rbcweatherapp.ui.composable.LocationEntry
import com.yinkaolu.rbcweatherapp.ui.composable.WeatherSummarySection
import com.yinkaolu.rbcweatherapp.ui.viewmodel.LocationInfo
import com.yinkaolu.rbcweatherapp.ui.viewmodel.WeatherSummary

@Composable
fun MainWeatherReportScreen(
    summary: WeatherSummary,
    onSearchLocation: (locationInfo: LocationInfo) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            WeatherSummarySection(
                weatherSummary = summary
            )
        }

        item {
            LocationEntry(
                onSearchLocation = onSearchLocation
            )
        }
    }
}