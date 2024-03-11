/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.MFScaffold

@Composable
fun ConfigScreen(
    viewModel: ConfigScreenViewModel = hiltViewModel(),
    backAction: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MFScaffold(text = "Settings", true, backAction = { backAction() }) {
        ConfigContent(uiState) { delayType, value ->
            viewModel.delayChanged(delayType, value)
        }
    }
}

@Composable
fun ConfigContent(
    uiState: ConfigScreenViewModel.ConfigState,
    onDelayChanged: (ConfigScreenViewModel.Delays, Float) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(all = 24.dp)) {
        item {
            SliderItem(ConfigScreenViewModel.Delays.Quick, uiState.quickDelayMs) { d, v ->
                onDelayChanged(
                    d,
                    v
                )
            }
            SliderItem(ConfigScreenViewModel.Delays.Slow, uiState.slowDelayMs) { d, v ->
                onDelayChanged(
                    d,
                    v
                )
            }
            SliderItem(ConfigScreenViewModel.Delays.Period, uiState.repeatablePeriod) { d, v ->
                onDelayChanged(
                    d,
                    v
                )
            }
        }
    }
}

@Composable
fun SliderItem(
    delayType: ConfigScreenViewModel.Delays,
    delay: Long,
    valueChanged: (ConfigScreenViewModel.Delays, Float) -> Unit
) {

    Column {
        Text(text = delayType.delayName)
        Row {
            var sliderValue by remember {
                mutableFloatStateOf(delay.toFloat())
            }
            Slider(
                value = sliderValue,
                steps = delayType.steps,
                valueRange = delayType.range,
                onValueChange = { sliderValue = it},
                onValueChangeFinished = { valueChanged(delayType, sliderValue)},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(8.dp)
            )
            Text(
                text = "$sliderValue ms",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
fun previewSlider() {
    SliderItem(delayType = ConfigScreenViewModel.Delays.Slow, 1000L) { _, _ -> }
}
