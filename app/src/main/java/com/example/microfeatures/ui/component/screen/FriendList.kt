/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.component.screen

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.component.Avatar
import com.example.microfeatures.ui.regular.RegularArchitectureViewModel


@Composable
fun FriendList(friendList: RegularArchitectureViewModel.UiState.FriendList) {
    Log.i("TEST", "Recomposing")
    LazyColumn {
        friendList.friendList.forEach { user ->
            item(key = user.id) {
                FriendItem(user)
            }
        }
    }

}

@Composable
fun FriendItem(user: SlowRepository.Friend) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Avatar(
            url = user.imageUrl,
            contentDescription = user.name,
            modifier = Modifier.size(48.dp)
        )
        Text(text = user.name, Modifier.padding(8.dp))
    }
}
