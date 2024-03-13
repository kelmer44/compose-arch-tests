/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.host

import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope

class ScreenHostUiModelImpl @AssistedInject constructor(
    userUimodelFactory: UserDataUiModel.Factory,
    friendsUiModelFactory: FriendsUiModel.Factory,
    timeUiModelFactory: TimeUiModel.Factory,
    @Assisted coroutineScope: CoroutineScope
) : ScreenHostUiModel{
    override val userUiModel: UserDataUiModel = userUimodelFactory.create(coroutineScope)
    override val friendsUiModel: FriendsUiModel = friendsUiModelFactory.create(coroutineScope)
    override val timeUiModel: TimeUiModel = timeUiModelFactory.create(coroutineScope)

    @AssistedFactory
    interface Factory: ScreenHostUiModel.Factory {

        override fun create(coroutineScope: CoroutineScope): ScreenHostUiModelImpl
    }

}