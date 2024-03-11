/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.repository.ContinuousRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val continuousRepository: ContinuousRepository
) : ViewModel() {

    val uiState = continuousRepository.getTime().stateIn(viewModelScope, SharingStarted.Lazily, "")
}