/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.repository.ImmediateRepository
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.regular.RegularArchitectureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SingleViewModelViewModel @Inject constructor(
    immediateRepository: ImmediateRepository,
    quickRepository: QuickRepository,
    slowRepository: SlowRepository
) : ViewModel() {

    val uiState: StateFlow<RegularArchitectureViewModel.UiState> =
        combine(
            slowRepository.getFriendList(1),
            quickRepository.getUserData(),
            immediateRepository.getTime()
        ) { friendList, userData, time ->
            RegularArchitectureViewModel.UiState.Loaded(
                RegularArchitectureViewModel.UiState.FriendList(friendList),
                userData,
                time
            )
        }.stateIn(viewModelScope, SharingStarted.Lazily, RegularArchitectureViewModel.UiState.InProgress)


}