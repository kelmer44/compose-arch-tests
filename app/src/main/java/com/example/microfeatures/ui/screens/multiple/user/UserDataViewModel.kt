/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.user

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.QuickRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    quickRepository: QuickRepository
) : ViewModel() {

    val uiState: StateFlow<UserDataState> = quickRepository
        .getUserData(1)
        .filterNotNull()
        .map {
            val userData = it.getOrNull()
            if (userData != null) {
                UserDataState.Loaded(userData)
            } else UserDataState.Error("Error loading user")
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UserDataState.Loading)

    sealed interface UserDataState {
        data object Loading : UserDataState
        data class Loaded(val userModel: UserModel) : UserDataState
        data class Error(val string: String) : UserDataState
    }
}