/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.repository

import com.example.microfeatures.datasource.UserDataSource
import com.example.microfeatures.datastore.MFDataStore
import com.example.microfeatures.model.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class QuickRepository @Inject constructor(
    private val dataStore: MFDataStore,
    private val dataSource: UserDataSource
) {

    fun getUserData(userId: Int): Flow<Result<UserModel>> {
        return flowOf(
            runCatching {
                dataSource.getUser(userId)
            }
        ).onStart {
            delay(dataStore.getQuickDelayMs().first())
        }
    }
}