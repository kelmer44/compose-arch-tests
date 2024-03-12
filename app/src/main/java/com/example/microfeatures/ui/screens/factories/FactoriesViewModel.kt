/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FactoriesViewModel @Inject constructor(
    friendsUiModelFactory: FriendsUiModel.Factory,
    userUiModelFactory: UserDataUiModel.Factory,
    timeUiModelFactory: TimeUiModel.Factory
) : ViewModel(){

    val friendsUiModel = friendsUiModelFactory.create(viewModelScope)
    val userUiModel = userUiModelFactory.create(viewModelScope)
    val timeUiModel = timeUiModelFactory.create(viewModelScope)
}