/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.host

import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModel
import kotlinx.coroutines.CoroutineScope

interface ScreenHostUiModel {

    val userUiModel: UserDataUiModel
    val friendsUiModel : FriendsUiModel
    val timeUiModel: TimeUiModel

    interface Factory {
        fun create(coroutineScope: CoroutineScope) : ScreenHostUiModel
    }
}