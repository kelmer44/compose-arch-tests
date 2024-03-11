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
import kotlinx.coroutines.flow.catch
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
            slowRepository.getFriendList(startingId),
            quickRepository.getUserData(startingId).filterNotNull(),
            continuousRepository.getTime()
        ) { friendList, userData, time ->

            val userData = userData.getOrNull()
            if (userData != null) {
                UiState.Loaded(
                    FriendList(friendList),
                    userData,
                    time
                )
            } else {
                UiState.Error("Errore retrieving user")
            }
        }
            .stateIn(viewModelScope, SharingStarted.Lazily, UiState.InProgress)

    sealed interface UiState {

        data class Loaded(
            val friendList: FriendList = FriendList(emptyList()),
            val userData: UserModel = UserModel(),
            val time: String = ""
        ) : UiState

        data object InProgress : UiState

        data class Error(val error: String) : UiState
    }

    companion object {
        private const val startingId: Int = 1
    }

}