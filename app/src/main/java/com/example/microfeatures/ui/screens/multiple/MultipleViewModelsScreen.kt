/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.FriendListView
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.TimeBar
import com.example.microfeatures.ui.component.screen.UserData
import com.example.microfeatures.ui.screens.multiple.friends.FriendListViewModel
import com.example.microfeatures.ui.screens.multiple.friends.rememberFriendListState
import com.example.microfeatures.ui.screens.multiple.time.rememberTimeState
import com.example.microfeatures.ui.screens.multiple.user.UserDataViewModel
import com.example.microfeatures.ui.screens.multiple.user.rememberUserDataState

@Composable
fun MultipleViewModelsScreen(backAction: () -> Unit) {
    MFScaffold("Multiple ViewModels", true, backAction) {

        Column(
            verticalArrangement = Arrangement.Top
        ) {
            TimeBar()
            UserPanel()
            FriendPanel()
        }
    }
}

@Composable
fun FriendPanel() {
    val rememberFriendListState by rememberFriendListState()
    when (val state = rememberFriendListState) {
        is FriendListViewModel.FriendListState.Error -> ErrorView(state.string)
        is FriendListViewModel.FriendListState.Loaded -> FriendListView(friendList = state.friendList)
        is FriendListViewModel.FriendListState.Loading -> LoadingView(modifier = Modifier.fillMaxWidth())

    }

}

@Composable
fun UserPanel() {
    val userDataState by rememberUserDataState()

    when (val state = userDataState) {
        is UserDataViewModel.UserDataState.Loaded -> UserData(userData = state.userModel)
        is UserDataViewModel.UserDataState.Loading -> LoadingView(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.surfaceContainer).padding(24.dp))
        is UserDataViewModel.UserDataState.Error -> ErrorView(state.string)
    }
}


@Composable
fun TimeBar() {
    val timeState by rememberTimeState()
    TimeBar(time = timeState)
}
