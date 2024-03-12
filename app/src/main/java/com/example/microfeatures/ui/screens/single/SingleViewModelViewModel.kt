/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.repository.ContinuousRepository
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SingleViewModelViewModel @Inject constructor(
    continuousRepository: ContinuousRepository,
    quickRepository: QuickRepository,
    slowRepository: SlowRepository
) : ViewModel() {

    val uiState: StateFlow<RegularArchitectureViewModel.UiState> =
        combine(
            slowRepository.getFriendList(1),
            quickRepository.getUserData(1).filterNotNull(),
            continuousRepository.getTime()
        ) { friendList, userData, time ->
            RegularArchitectureViewModel.UiState.Loaded(
                FriendList(friendList),
                userData.getOrThrow(),
                time
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                RegularArchitectureViewModel.UiState.InProgress
            )

    val timeState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.time
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RegularArchitectureViewModel.UiState.InProgress
        )

    val userState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.userData
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RegularArchitectureViewModel.UiState.InProgress
        )
    val friendState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.time
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RegularArchitectureViewModel.UiState.InProgress
        )

}