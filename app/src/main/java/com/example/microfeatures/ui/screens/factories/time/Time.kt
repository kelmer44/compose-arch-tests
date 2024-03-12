/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.time

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.TimeBar

@Composable
fun Time(uiModel: TimeUiModel) {

    when(val uiState = uiModel.uiState.collectAsStateWithLifecycle().value){
        is TimeUiState.Loaded -> TimeBar(time = uiState.string)
        is TimeUiState.Loading -> LoadingView()
    }
}