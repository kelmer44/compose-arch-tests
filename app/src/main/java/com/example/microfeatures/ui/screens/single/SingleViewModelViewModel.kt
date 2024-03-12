/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.ContinuousRepository
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SingleViewModelViewModel @Inject constructor(
    continuousRepository: ContinuousRepository,
    quickRepository: QuickRepository,
    slowRepository: SlowRepository
) : ViewModel() {


    private val userIdState: MutableStateFlow<Int> = MutableStateFlow(RegularArchitectureViewModel.startingId)

    fun onFriendClicked(user: UserModel) {
        userIdState.value = user.userId
    }

    val uiState: StateFlow<RegularArchitectureViewModel.UiState> =
        userIdState.flatMapLatest {
            combine(
                slowRepository.getFriendList(it),
                quickRepository.getUserData(it).filterNotNull(),
                continuousRepository.getTime(),
            ) { friendList, userData, time ->
                val thisUserData = userData.getOrNull()
                if (thisUserData != null) {
                    RegularArchitectureViewModel.UiState.Loaded(
                        FriendList(friendList),
                        thisUserData,
                        time
                    )
                } else {
                    RegularArchitectureViewModel.UiState.Error("Error retrieving user")
                }
            }
        }
            .stateIn(viewModelScope, SharingStarted.Lazily, RegularArchitectureViewModel.UiState.InProgress)

    val timeState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.time
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ""
        )

    val userState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.userData
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            UserModel()
        )
    val friendState = uiState
        .filterIsInstance<RegularArchitectureViewModel.UiState.Loaded>().map {
            it.friendList
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            FriendList(emptyList())
        )

}