/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.selectionscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.microfeatures.ui.component.MFScaffold

@Composable
fun SelectionScreen(
    onRegularClicked: () -> Unit,
    onMultipleViewModelsClicked: () -> Unit,
    onSingleViewModelClicked: () -> Unit,
    onFactoriesClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    MFScaffold("MicroFeatures test", false) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onRegularClicked() }) {
                Text(text = "Regular architecture")
            }
            Button(onClick = { onMultipleViewModelsClicked() }) {
                Text(text = "MicroFeatures with multiple ViewModels")

            }
            Button(onClick = { onFactoriesClicked() }) {
                Text(text = "MicroFeatures with factories")
            }
//            Button(onClick = { onSingleViewModelClicked() }) {
//                Text(text = "MicroFeatures with single ViewModel")
//            }

            Button(onClick = { onSettingsClicked() }) {
                Text(text = "Settings")
            }
        }
    }
}