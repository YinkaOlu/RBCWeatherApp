package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yinkaolu.rbcweatherapp.ui.composable.ForcastWeatherSummaryItem
import com.yinkaolu.rbcweatherapp.ui.composable.LocationSummarySection
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.ForcastSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary

@Composable
fun ForcastDetailScreen(
    forcastSummary: ForcastSummary,
    locationSummary: LocationSummary?,
    onBack: () -> Unit
) {
    BackHandler {
        onBack()
    }
    Column {
        locationSummary?.let {
            LocationSummarySection(locationSummary = locationSummary)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp)
        ) {
            forcastSummary.weatherSummaries.forEach {
                item {
                    ForcastWeatherSummaryItem(it)
                }
            }
        }
    }
}
