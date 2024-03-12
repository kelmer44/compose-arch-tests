/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.user

import javax.inject.Inject

class UserComposableFactoryImpl @Inject constructor() : UserComposableFactory {
    override fun create(): UserComposable = { uiModel ->
        User(uiModel)
    }
}