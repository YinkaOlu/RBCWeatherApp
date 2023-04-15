package com.yinkaolu.rbcweatherapp.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.WeatherSummary

@Composable
fun ForcastWeatherSummaryItem(
    weatherSummary: WeatherSummary
) {
    Card(
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherSummary.dateString,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherSummary.temperature,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherSummary.weatherDescription,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
            )
        }
    }
}