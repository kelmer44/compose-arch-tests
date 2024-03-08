/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.repository

import com.example.microfeatures.utils.AvatarSize
import com.example.microfeatures.utils.Delays
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import java.util.UUID
import javax.inject.Inject

class QuickRepository @Inject constructor() {

    fun getUserData(): Flow<UserData> {
        return flowOf(
            UserData(
                "Gabriel Sanmartín",
                UUID.randomUUID().toString(),
                "https://i.pravatar.cc/${AvatarSize.size}?img=$avatarId"
            )
        ).onStart {
            delay(Delays.quick)
        }
    }

    data class UserData(
        val name: String = "",
        val userId: String = "",
        val avatarUrl: String = ""
    )

    companion object {
        const val avatarId = 50
    }
}