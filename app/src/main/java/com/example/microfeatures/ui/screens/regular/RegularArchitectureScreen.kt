/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.regular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.FriendListView
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.TimeBar
import com.example.microfeatures.ui.component.screen.UserData


@Composable
fun RegularArchitectureScreen(
    viewModel: RegularArchitectureViewModel = hiltViewModel(),
    backAction: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MFScaffold(text = "Regular architecture", showBackButton = true, backAction) {
        when (val state = uiState) {
            is RegularArchitectureViewModel.UiState.InProgress ->
                LoadingView(modifier = Modifier.fillMaxSize())

            is RegularArchitectureViewModel.UiState.Loaded ->
                Loaded(state)

            RegularArchitectureViewModel.UiState.Error -> ErrorView(modifier = Modifier.fillMaxSize())
        }
    }

}

@Composable
fun Loaded(uiState: RegularArchitectureViewModel.UiState.Loaded) {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        TimeBar(uiState.time)
        val userData by remember { derivedStateOf { uiState.userData } }
        UserData(userData)
        val friendList by remember { derivedStateOf { uiState.friendList } }
        FriendListView(friendList = friendList)
    }
}