/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.time

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TimeUiModel {
    val uiState: StateFlow<TimeUiState>

    interface Factory {
        fun create(coroutineScope: CoroutineScope) : TimeUiModel
    }
}

sealed interface TimeUiState {
    data object Loading: TimeUiState
    data class Loaded(val string: String): TimeUiState
}

typealias TimeComposable = @Composable (
    uiModel: TimeUiModel
) -> Unit

interface TimeComposableFactory {
    fun create() : TimeComposable
}