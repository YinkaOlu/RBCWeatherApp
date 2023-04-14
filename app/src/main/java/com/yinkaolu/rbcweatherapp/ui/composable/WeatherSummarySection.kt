package com.yinkaolu.rbcweatherapp.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yinkaolu.rbcweatherapp.ui.theme.RBCWeatherAppTheme
import com.yinkaolu.rbcweatherapp.ui.viewmodel.WeatherSummary

@Composable
fun WeatherSummarySection(
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
            Text(
                text = weatherSummary.cityName,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontStyle = MaterialTheme.typography.h3.fontStyle,
            )

            AsyncImage(
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                model = "https://openweathermap.org/img/wn/${weatherSummary.icon}@2x.png",
                contentDescription = "Weather Icon"
            )

            Text(
                text = weatherSummary.temperature,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontStyle = MaterialTheme.typography.h4.fontStyle,
            )

            Text(
                text = weatherSummary.weatherDescription,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontStyle = MaterialTheme.typography.h4.fontStyle,
            )
            
            Button(onClick = {}) {
                Text(text = "View More")
            }
        }
    }
}