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

class SlowRepository @Inject constructor(
    private val dataStore: MFDataStore
) {
    private fun userList() = listOf(
        UserModel(1, "John", "New York"),
        UserModel(2, "Mike", "Amsterdam"),
        UserModel(3, "Susan", "Berlin"),
        UserModel(4, "Tom", "London"),
        UserModel(5, "Lily", "Madrid"),
        UserModel(6, "Melissa", "Rome"),
        UserModel(7, "Mike", "Vienna"),
        UserModel(8, "Andrew", "Sao Paulo"),
    )

    fun getFriendList(userId: Int): Flow<List<UserModel>> {
        return flowOf(
            userList()
        )
            .onStart {
                delay(dataStore.getSlowDelayMs().first())
            }
    }
}

