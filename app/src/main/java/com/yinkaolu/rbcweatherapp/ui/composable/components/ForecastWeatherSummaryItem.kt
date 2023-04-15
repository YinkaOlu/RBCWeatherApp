package com.yinkaolu.rbcweatherapp.ui.composable.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yinkaolu.rbcweatherapp.R
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

/**
 * UI representing the Forecast items on the Forecast detail page
 */
@Composable
fun ForecastWeatherSummaryItem(
    weatherSummary: WeatherSummary
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit,
                model = "https://openweathermap.org/img/wn/${weatherSummary.icon}@2x.png",
                contentDescription = "Weather Icon"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherSummary.dateString,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherSummary.temperature,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.weather_observation),
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = weatherSummary.weatherDescription,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
            )
        }
    }
}