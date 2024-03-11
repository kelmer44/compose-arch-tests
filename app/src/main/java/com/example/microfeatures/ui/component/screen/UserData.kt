/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.microfeatures.model.UserModel
import com.example.microfeatures.ui.component.Avatar

@Composable
fun UserData(userData: UserModel) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Avatar(
                url = userData.avatarUrl,
                contentDescription = userData.name,
                modifier = Modifier.size(72.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = userData.name, style = MaterialTheme.typography.headlineSmall)
                Text(text = userData.city, style = MaterialTheme.typography.bodySmall)
            }
        }
}

@Preview
@Composable
fun previewUserData(){

    UserData(userData = UserModel(
        50,
        "Gabriel Sanmartin",
        "Santiago de Compostela"
    ))
}