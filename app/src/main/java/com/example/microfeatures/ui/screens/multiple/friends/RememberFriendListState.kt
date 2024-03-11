/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel

@Composable
fun rememberFriendListState() : State<RegularArchitectureViewModel.UiState.FriendList> {
    val viewModel: FriendListViewModel = hiltViewModel()

    return viewModel.uiState.collectAsStateWithLifecycle()
}