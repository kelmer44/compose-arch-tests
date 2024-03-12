/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.single.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel
import com.example.microfeatures.ui.screens.single.SingleViewModelViewModel

@Composable
fun rememberFriendListState() : State<FriendList> {
    val viewModel: SingleViewModelViewModel = hiltViewModel()

    return viewModel.friendState.collectAsStateWithLifecycle()
}