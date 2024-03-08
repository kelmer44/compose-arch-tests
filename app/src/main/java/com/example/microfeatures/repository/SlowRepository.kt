/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.repository

import android.util.Log
import com.example.microfeatures.utils.AvatarSize
import com.example.microfeatures.utils.Delays
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SlowRepository @Inject constructor() {
    private fun userList() = listOf(
        Friend(1, "John"),
        Friend(2, "Mike"),
        Friend(3, "Susan"),
        Friend(4, "Tom"),
        Friend(5, "Lily"),
        Friend(6, "Melissa"),
        Friend(7, "Mike"),
        Friend(8, "Andrew"),
    )

    fun getFriendList(userId: Int): Flow<List<Friend>> {
        return flowOf(
            userList()
        )
            .onStart {
                delay(Delays.slow)
            }
    }


    data class Friend(
        val id: Int,
        val name: String,
        val imageUrl: String = getImageUrl(id)
    )


    companion object {

        fun getImageUrl(id: Int): String {
            return "https://i.pravatar.cc/${AvatarSize.size}?img=$id"
        }
    }
}

