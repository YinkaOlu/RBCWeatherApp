package com.yinkaolu.rbcweatherapp.ui.composable.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.composable.components.LocationEntry
import com.yinkaolu.rbcweatherapp.ui.composable.components.LocationSummarySection
import com.yinkaolu.rbcweatherapp.ui.composable.components.WeatherSummarySection
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationInfo
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

@Composable
fun MainWeatherReportScreen(
    weatherSummary: WeatherSummary,
    locationSummary: LocationSummary?,
    onSearchLocation: (locationInfo: LocationInfo) -> Unit,
    onViewMore: () -> Unit
) {
    var hideSearch by remember { mutableStateOf(true) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {

        item {
            Button(
                onClick = { hideSearch = !hideSearch }
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )

                    Text(if(hideSearch) {
                        stringResource(R.string.open_location_button_hint)
                    } else {
                        stringResource(R.string.close_location_button_hint)
                    })
                }
            }
        }

        if (!hideSearch) {
            item {
                LocationEntry(
                    onSearchLocation = onSearchLocation
                )
            }
        }



        locationSummary?.let {
            item {
                LocationSummarySection(locationSummary = locationSummary)
            }
        }

        item {
            WeatherSummarySection(
                weatherSummary = weatherSummary,
                onClick = onViewMore
            )
        }


    }
}