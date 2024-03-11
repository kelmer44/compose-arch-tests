/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.multiple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.FriendList
import com.example.microfeatures.ui.component.screen.TimeBar
import com.example.microfeatures.ui.component.screen.UserData
import com.example.microfeatures.ui.screens.multiple.friends.rememberFriendListState
import com.example.microfeatures.ui.screens.multiple.time.rememberTimeState
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
    FriendList(friendList = rememberFriendListState)
}

@Composable
fun UserPanel() {
    val userDataState by rememberUserDataState()
    UserData(userData = userDataState)
}

@Composable
fun TimeBar() {
    val timeState by rememberTimeState()
    TimeBar(time = timeState)
}
