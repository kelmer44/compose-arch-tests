/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.example.microfeatures.model.FriendList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface FriendsUiModel {
    val uiState: StateFlow<FriendsUiState>
    interface Factory {
        fun create(coroutineScope: CoroutineScope): FriendsUiModel
    }
}

sealed interface FriendsUiState {
    data object Loading: FriendsUiState
    data class Error(val string: String): FriendsUiState
    data class Loaded(val friendList: FriendList): FriendsUiState
}

typealias FriendListComposable = @Composable (
    uiModel: FriendsUiModel
) -> Unit

interface FriendListComposableFactory {
    fun create() : FriendListComposable
}