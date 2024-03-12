/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.time

import com.example.microfeatures.repository.ContinuousRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TimeUiModelImpl @AssistedInject constructor(
    private val continuousRepository: ContinuousRepository,
    @Assisted private val coroutineScope: CoroutineScope
) : TimeUiModel {
    override val uiState: StateFlow<TimeUiState> =
        continuousRepository.getTime().map {
            TimeUiState.Loaded(it)
        }.stateIn(
            coroutineScope, SharingStarted.Lazily, TimeUiState.Loading
        )


    @AssistedFactory
    interface Factory: TimeUiModel.Factory {
        override fun create(coroutineScope: CoroutineScope): TimeUiModelImpl
    }
}