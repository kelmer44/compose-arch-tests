/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.regular

import android.service.autofill.UserData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.FriendListView
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.TimeBar
import com.example.microfeatures.ui.component.screen.UserData


@Composable
fun RegularArchitectureScreen(
    viewModel: RegularArchitectureViewModel = hiltViewModel(),
    backAction: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    MFScaffold(text = "Regular architecture", showBackButton = true, backAction) {
        when (val state = uiState) {
            is RegularArchitectureViewModel.UiState.InProgress ->
                LoadingView(modifier = Modifier.fillMaxSize())

            is RegularArchitectureViewModel.UiState.Loaded -> {
                val lambda: (UserModel) -> Unit = remember(state.userData.userId) {
                    { viewModel.onFriendClicked(it) }
                }
                Loaded(state, lambda)
            }

            is RegularArchitectureViewModel.UiState.Error -> ErrorView(
                state.error,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
fun Loaded(
    uiState: RegularArchitectureViewModel.UiState.Loaded,
    onFriendClicked: (UserModel) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        TimeBar(uiState.time)
        UserData(uiState.userData)
        val friendList by remember(uiState) { mutableStateOf(uiState.friendList) }
        FriendListView(friendList = friendList, onFriendClicked = onFriendClicked)
    }
}