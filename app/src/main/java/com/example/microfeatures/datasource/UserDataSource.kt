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
        UserModel(3, "Susan", "Berlin"),
        UserModel(4, "Tom", "London"),
        UserModel(5, "Lily", "Madrid"),
        UserModel(6, "Melissa", "Rome"),
        UserModel(7, "Mike", "Vienna"),
        UserModel(8, "Andrew", "Sao Paulo"),
        UserModel(9, "Tim", "New York"),
        UserModel(10, "Amy", "Amsterdam"),
        UserModel(11, "Sophie", "Paris"),
        UserModel(12, "Patrick", "Cuenca"),
        UserModel(13, "Francis", "Barcelona"),
        UserModel(14, "Jade", "San Francisco"),
        UserModel(15, "Kim", "Los Angeles"),
        UserModel(16, "Kate", "Portland"),
        UserModel(17, "George", "Helsinki"),
        UserModel(18, "Philip", "Stockholm"),
        UserModel(19, "Susan", "Dublin"),
        UserModel(20, "Paul", "Glasgow"),
    ).associateBy { it.userId }

    fun getUsers() = userList.entries
    fun getUser(id: Int) =
        if (id == 3) throw IllegalStateException("No user with given id") else userList.getValue(id)

    fun getFriendsByUser(id: Int): List<UserModel> {
        // By using the same random using the id as a seed, we ensure we get the same size and the
        // same friends list using that size, provided we stay within the same runtime. i.e. the
        // method will be deterministic within the same runtime
        val random = Random(id)
        val size = random.nextInt(1..userList.size)
        return userList.values.shuffled(random).subList(0, size)
    }
}