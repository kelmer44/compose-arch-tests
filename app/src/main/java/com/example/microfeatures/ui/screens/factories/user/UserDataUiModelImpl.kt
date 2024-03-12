/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.user

import com.example.microfeatures.repository.QuickRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserDataUiModelImpl @AssistedInject constructor(
    private val quickRepository: QuickRepository,
    @Assisted private val coroutineScope: CoroutineScope
) : UserDataUiModel {
    override val uiState: StateFlow<UserDataUiState>
        get() = quickRepository.getUserData(1)
            .map {
                val userModel = it.getOrNull()
                if (userModel != null) {
                    UserDataUiState.Loaded(userModel)
                } else UserDataUiState.Error("Error retrieving user")
            }.stateIn(coroutineScope, SharingStarted.Lazily, UserDataUiState.Loading)


    @AssistedFactory
    interface Factory : UserDataUiModel.Factory {
        override fun create(coroutineScope: CoroutineScope): UserDataUiModelImpl
    }

}