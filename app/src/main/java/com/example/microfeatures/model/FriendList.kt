package com.example.microfeatures.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
@Stable
data class FriendList(val friendList: List<UserModel>)