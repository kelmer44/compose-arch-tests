/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.user

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeatures.ui.component.screen.ErrorView
import com.example.microfeatures.ui.component.screen.LoadingView
import com.example.microfeatures.ui.component.screen.UserData

@Composable
fun User(uiModel: UserDataUiModel) {
    Log.i("FRIENDS", "Repainting UserData")
    when(val uiState = uiModel.uiState.collectAsState().value){
        is UserDataUiState.Error -> ErrorView(error = uiState.string)
        is UserDataUiState.Loaded -> UserData(userData = uiState.userModel)
        is UserDataUiState.Loading -> LoadingView()
    }
}