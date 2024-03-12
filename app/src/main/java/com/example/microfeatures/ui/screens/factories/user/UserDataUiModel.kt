/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.user

import androidx.compose.runtime.Composable
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface UserDataUiModel {
    val uiState: StateFlow<UserDataUiState>

    interface Factory {
        fun create(coroutineScope: CoroutineScope): UserDataUiModel
    }
}


sealed interface UserDataUiState {
    data object Loading: UserDataUiState
    data class Loaded(val userModel: UserModel): UserDataUiState
    data class Error(val string: String): UserDataUiState
}

typealias UserComposable = @Composable (
    uiModel: UserDataUiModel
) -> Unit

interface UserComposableFactory {
    fun create() : UserComposable
}