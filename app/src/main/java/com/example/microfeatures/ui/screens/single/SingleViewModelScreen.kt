/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.single

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.FriendListView
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.UserData
import com.example.microfeatures.ui.screens.regular.RegularArchitectureViewModel
import com.example.microfeatures.ui.screens.single.friends.rememberFriendListState
import com.example.microfeatures.ui.screens.single.time.rememberTimeState
import com.example.microfeatures.ui.screens.single.user.rememberUserDataState

@Composable
fun SingleViewModelScreen(
    viewModel: SingleViewModelViewModel = hiltViewModel(),
    backAction: () -> Unit
) {
    val screenState = viewModel.uiState.collectAsStateWithLifecycle()
    MFScaffold("Single ViewModels", true, backAction = backAction) {
        when(val state =screenState.value) {
            is RegularArchitectureViewModel.UiState.Error -> ErrorView(error = state.error)
            RegularArchitectureViewModel.UiState.InProgress -> LoadingView()
            is RegularArchitectureViewModel.UiState.Loaded -> Content()
        }

    }
}

@Composable
fun Content() {
    Column {
        TimeBar()
        UserPanel()
        FriendPanel()
    }
}

@Composable
fun FriendPanel() {
    val rememberFriendListState by rememberFriendListState()
    FriendListView(friendList = rememberFriendListState) {
    }
}

@Composable
fun UserPanel() {
    val userDataState by rememberUserDataState()
    UserData(userData = userDataState)
}


@Composable
fun TimeBar() {
    val timeState by rememberTimeState()
    com.example.microfeatures.ui.component.screen.TimeBar(time = timeState)
}
