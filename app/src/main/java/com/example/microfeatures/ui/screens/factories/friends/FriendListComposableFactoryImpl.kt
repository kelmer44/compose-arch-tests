/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.friends

import javax.inject.Inject

class FriendListComposableFactoryImpl @Inject constructor() : FriendListComposableFactory {
    override fun create(): FriendListComposable = { uiModel -> Friends(uiModel = uiModel) }
}