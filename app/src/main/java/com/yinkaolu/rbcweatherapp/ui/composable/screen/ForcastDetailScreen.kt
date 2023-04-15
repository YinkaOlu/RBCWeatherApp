package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yinkaolu.rbcweatherapp.R
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
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(R.string.close_content_description)
            )
        }
        locationSummary?.let {
            LocationSummarySection(locationSummary = locationSummary)
        }

        LazyVerticalGrid(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
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
