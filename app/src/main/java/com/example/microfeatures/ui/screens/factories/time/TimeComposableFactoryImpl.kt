/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.time

import javax.inject.Inject

class TimeComposableFactoryImpl @Inject constructor() : TimeComposableFactory {
    override fun create(): TimeComposable = { uiModel ->
        Time(uiModel = uiModel)
    }
}