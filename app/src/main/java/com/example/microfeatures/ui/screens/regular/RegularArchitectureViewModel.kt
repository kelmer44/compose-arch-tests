/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.regular

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.ContinuousRepository
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.repository.SlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RegularArchitectureViewModel @Inject constructor(
    quickRepository: QuickRepository,
    continuousRepository: ContinuousRepository,
    slowRepository: SlowRepository
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        combine(
            slowRepository.getFriendList(1),
            quickRepository.getUserData().filterNotNull(),
            continuousRepository.getTime()
        ) { friendList, userData, time ->

            UiState.Loaded(
                FriendList(friendList),
                userData,
                time
            )
        }
            .stateIn(viewModelScope, SharingStarted.Lazily, UiState.InProgress)

    sealed interface UiState {

        data class Loaded(
            val friendList: FriendList = FriendList(emptyList()),
            val userData: UserModel = UserModel(),
            val time: String = ""
        ) : UiState

        data object InProgress : UiState

        data object Error: UiState
    }

}