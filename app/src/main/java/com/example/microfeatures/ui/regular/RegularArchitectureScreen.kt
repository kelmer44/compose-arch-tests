/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.regular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.component.screen.FriendList
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
                Loading()

            is RegularArchitectureViewModel.UiState.Loaded ->
                Loaded(state)
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
        FriendList(friendList = friendList)
    }
}


@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}