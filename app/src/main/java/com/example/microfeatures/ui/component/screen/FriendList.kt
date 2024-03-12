/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.component.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.microfeatures.model.FriendList
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.ui.component.Avatar


@Composable
fun FriendListView(friendList: FriendList, onFriendClicked: (UserModel) -> Unit) {
    Log.i("TEST", "Recomposing")
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Friend list".uppercase(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn {
            friendList.friendList.forEach { user ->
                item(key = user.userId) {
                    FriendItem(user, onFriendClicked)
                }
            }
        }
    }
}

@Composable
fun FriendItem(user: UserModel, onFriendClicked: (UserModel) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onFriendClicked(user)
            }
    ) {
        Avatar(
            url = user.avatarUrl,
            contentDescription = user.name,
            modifier = Modifier.size(48.dp)
        )
        Column {
            Text(
                text = user.name,
                Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = user.city,
                Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview
@Composable
fun previewFriendItem() {
    FriendItem(
        user = UserModel(1, "Mariano Rajoy", "Pontevedra"),
        onFriendClicked = {}
    )
}