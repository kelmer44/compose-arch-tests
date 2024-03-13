/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.friends

import android.util.Log
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.repository.SlowRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class FriendsUiModelImpl @AssistedInject constructor(
    private val slowRepository: SlowRepository,
    @Assisted private val coroutineScope: CoroutineScope
) : FriendsUiModel{
    override val uiState: StateFlow<FriendsUiState>
        get() = slowRepository.getFriendList(1)
            .map { FriendsUiState.Loaded(FriendList(it)) }
            .onEach { Log.i("FRIENDS", "Friends emitting from $this!") }
            .stateIn(coroutineScope, SharingStarted.Lazily, FriendsUiState.Loading)

    @AssistedFactory
    interface Factory: FriendsUiModel.Factory {
        override fun create(coroutineScope: CoroutineScope): FriendsUiModelImpl
    }
}