/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.host

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactory
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactory
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactory

@Composable
fun ScreenHostContent(
    uiModel: ScreenHostUiModel,
    userComposableFactory: UserComposableFactory,
    timeComposableFactory: TimeComposableFactory,
    friendsListComposableFactory: FriendListComposableFactory,
    modifier: Modifier = Modifier
) {

    val time = remember { timeComposableFactory.create() }
    val user = remember { userComposableFactory.create() }
    val friends = remember { friendsListComposableFactory.create() }

    Column(modifier) {
        time(uiModel.timeUiModel)
        user(uiModel.userUiModel)
        friends(uiModel.friendsUiModel)
    }

}