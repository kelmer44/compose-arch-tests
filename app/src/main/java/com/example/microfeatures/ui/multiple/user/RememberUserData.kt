/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.multiple.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.repository.QuickRepository

@Composable
internal fun rememberUserDataState(): State<QuickRepository.UserData> {
    val viewModel: UserDataViewModel = hiltViewModel()

    return viewModel.uiState.collectAsStateWithLifecycle()
}