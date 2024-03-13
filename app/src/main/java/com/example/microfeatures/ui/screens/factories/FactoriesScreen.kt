/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.microfeatures.ui.component.MFScaffold
import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactory
import com.example.microfeatures.ui.screens.factories.host.ScreenHostContent
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactory
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactory

@Composable
fun FactoriesScreen(
    friendListComposableFactory: FriendListComposableFactory,
    userComposableFactory: UserComposableFactory,
    timeComposableFactory: TimeComposableFactory,
    backAction: () -> Unit,
    factoriesViewModel: FactoriesViewModel = hiltViewModel()
) {
    Log.w("FRIENDS", "Repainting FactoriesScreen")
    MFScaffold(text = "MicroFeatures with Factories", true, backAction) {
        ScreenHostContent(
            uiModel = factoriesViewModel.screenHostUiModel,
            userComposableFactory = userComposableFactory,
            timeComposableFactory = timeComposableFactory,
            friendsListComposableFactory = friendListComposableFactory
        )
    }
}