/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModel
import com.example.microfeatures.ui.screens.factories.host.ScreenHostUiModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FactoriesViewModel @Inject constructor(
    screenHostUiModelFactory: ScreenHostUiModel.Factory
) : ViewModel(){

    val screenHostUiModel = screenHostUiModelFactory.create(viewModelScope)
}