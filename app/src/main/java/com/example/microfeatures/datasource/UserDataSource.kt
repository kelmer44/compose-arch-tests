/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.datasource

import com.example.microfeatures.model.UserModel
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class UserDataSource @Inject constructor() {
    private val userList = listOf(
        UserModel(1, "John", "New York"),
        UserModel(2, "Mike", "Amsterdam"),
//        UserModel(3, "Susan", "Berlin"),
        UserModel(4, "Tom", "London"),
        UserModel(5, "Lily", "Madrid"),
        UserModel(6, "Melissa", "Rome"),
        UserModel(7, "Mike", "Vienna"),
        UserModel(8, "Andrew", "Sao Paulo"),
        UserModel(9, "Tim", "New York"),
        UserModel(10, "Amy", "Amsterdam"),
        UserModel(11, "Sophie", "New York"),
        UserModel(12, "Patrick", "Amsterdam"),
        UserModel(13, "Francis", "Berlin"),
        UserModel(14, "Jade", "London"),
        UserModel(15, "Kim", "Madrid"),
        UserModel(16, "Kate", "Rome"),
        UserModel(17, "George", "Vienna"),
        UserModel(18, "Philip", "Sao Paulo"),
        UserModel(19, "Susan", "Sao Paulo"),
        UserModel(20, "Paul", "Sao Paulo"),
    ).associateBy { it.userId }

    fun getUsers() = userList.entries

    fun getUser(id: Int) = if(userList.containsKey(id)) userList.getValue(id) else
        throw IllegalStateException("No user with given id")

    fun getFriendsByUser(id: Int): List<UserModel> {
        //get random size
        val size = Random.nextInt(0..userList.size)
        return userList.values.shuffled().subList(0, size)
    }
}