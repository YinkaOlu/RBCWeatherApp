package com.yinkaolu.rbcweatherapp.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationInfo

@Composable
fun LocationEntry(
    onSearchLocation: (location: LocationInfo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var cityText by remember { mutableStateOf("") }
        var stateText by remember { mutableStateOf("") }
        var countryText by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                    Text(stringResource(R.string.city_hint))
            },
            value = cityText,
            onValueChange = { cityText = it }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(stringResource(R.string.state_hint))
            },
            value = stateText,
            onValueChange = { stateText = it }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(stringResource(R.string.country_hint))
            },
            value = countryText,
            onValueChange = { countryText = it }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            onSearchLocation(
                LocationInfo(
                    cityName = cityText,
                    state = stateText,
                    country = countryText
                )
            )
        }) {
            Text(text = stringResource(R.string.search_button_text))
        }

    }
}