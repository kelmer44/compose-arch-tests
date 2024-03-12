/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FriendListViewModel @Inject constructor(
    slowRepository: SlowRepository
) : ViewModel() {


    private val userIdState: MutableStateFlow<Int> = MutableStateFlow(startingId)
    fun onFriendClicked(user: UserModel) {
        userIdState.value = user.userId
    }

    val uiState: StateFlow<FriendListState> =
        userIdState.flatMapLatest { slowRepository.getFriendList(it) }
            .map { FriendListState.Loaded(FriendList(friendList = it)) as FriendListState }
            .onStart {
                emit(FriendListState.Loading)
            }
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

    companion object {
        const val startingId: Int = 1
    }
}