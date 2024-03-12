/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.regular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.ContinuousRepository
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.repository.SlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RegularArchitectureViewModel @Inject constructor(
    quickRepository: QuickRepository,
    continuousRepository: ContinuousRepository,
    slowRepository: SlowRepository
) : ViewModel() {

    private val userIdState: MutableStateFlow<Int> = MutableStateFlow(startingId)

    fun onFriendClicked(user: UserModel) {
        userIdState.value = user.userId
    }

    val uiState: StateFlow<UiState> =
        userIdState.flatMapLatest {
            combine(
                slowRepository.getFriendList(it),
                quickRepository.getUserData(it).filterNotNull(),
                continuousRepository.getTime(),
            ) { friendList, userData, time ->
                val thisUserData = userData.getOrNull()
                if (thisUserData != null) {
                    UiState.Loaded(
                        FriendList(friendList),
                        thisUserData,
                        time
                    )
                } else {
                    UiState.Error("Error retrieving user")
                }
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