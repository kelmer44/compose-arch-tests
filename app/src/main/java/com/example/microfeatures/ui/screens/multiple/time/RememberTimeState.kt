/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.ui.screens.multiple.user.UserDataViewModel

@Composable
internal fun rememberTimeState(): State<String> {
    val viewModel: TimeViewModel = hiltViewModel()
    return viewModel.uiState.collectAsStateWithLifecycle()
}