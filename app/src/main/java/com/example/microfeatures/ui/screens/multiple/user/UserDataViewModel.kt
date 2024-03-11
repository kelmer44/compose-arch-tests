/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.repository.QuickRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val quickRepository: QuickRepository
) : ViewModel() {

    val uiState = quickRepository.getUserData()
        .stateIn(viewModelScope, SharingStarted.Lazily, QuickRepository.UserData())
}