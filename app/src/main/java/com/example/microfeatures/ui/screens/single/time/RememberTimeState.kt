/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.single.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.repository.QuickRepository
import com.example.microfeatures.ui.screens.multiple.user.UserDataViewModel
import com.example.microfeatures.ui.screens.single.SingleViewModelViewModel

@Composable
internal fun rememberTimeState(): State<String> {
    val viewModel: SingleViewModelViewModel = hiltViewModel()
    return viewModel.timeState.collectAsStateWithLifecycle()
}