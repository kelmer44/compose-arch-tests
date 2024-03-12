/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.friends

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.FriendListView
import com.example.microfeatures.ui.component.screen.LoadingView

@Composable
fun Friends(uiModel: FriendsUiModel) {
    Log.i("Friends", "Loading friends")
    when(val uiState = uiModel.uiState.collectAsState().value){
        is FriendsUiState.Error -> ErrorView(error = uiState.string)
        is FriendsUiState.Loaded -> FriendListView(friendList = uiState.friendList) {}
        is FriendsUiState.Loading -> LoadingView()
    }
}