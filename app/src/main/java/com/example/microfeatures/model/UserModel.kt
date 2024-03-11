package com.example.microfeatures.model

import com.example.microfeatures.utils.AvatarSize

data class UserModel(
    val userId: Int = 0,
    val name: String = "",
    val city: String = "",
) {
    val avatarUrl = "https://i.pravatar.cc/${AvatarSize.size}?img=$userId"
}