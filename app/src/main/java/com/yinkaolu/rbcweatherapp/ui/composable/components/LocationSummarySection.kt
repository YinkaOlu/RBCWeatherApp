package com.yinkaolu.rbcweatherapp.ui.composable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.yinkaolu.rbcweatherapp.ui.viewmodel.model.LocationSummary

/**
 * Component that renders the location details in main/detail page
 */
@Composable
fun LocationSummarySection(
    locationSummary: LocationSummary
) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = locationSummary.cityName,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontStyle = MaterialTheme.typography.h4.fontStyle,
        )

        locationSummary.stateName?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )
        }

        locationSummary.countryName?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
            )
        }
    }
}
