/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.SlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FriendListViewModel @Inject constructor(
    slowRepository: SlowRepository
) : ViewModel() {

    val uiState: StateFlow<FriendListState> = slowRepository.getFriendList(1)
        .map { FriendListState.Loaded(FriendList(friendList = it)) }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            FriendListState.Loading
        )

    sealed interface FriendListState {
        data object Loading : FriendListState
        data class Loaded(val friendList: FriendList) : FriendListState
        data class Error(val string: String) : FriendListState
    }
}