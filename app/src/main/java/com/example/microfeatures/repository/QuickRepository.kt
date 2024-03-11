/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.repository

import com.example.microfeatures.datastore.MFDataStore
import com.example.microfeatures.model.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class QuickRepository @Inject constructor(
    private val dataStore: MFDataStore
) {

    fun getUserData(): Flow<UserModel?> {
        return flowOf(
            UserModel(
                50,
                "Gabriel Sanmartín",
                city = "Santiago de Compostela",
            )
        ).onStart {
            delay(dataStore.getQuickDelayMs().first())
        }
    }
}