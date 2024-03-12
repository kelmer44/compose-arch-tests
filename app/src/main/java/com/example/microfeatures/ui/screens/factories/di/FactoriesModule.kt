/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.factories.di

import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactory
import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactoryImpl
import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModel
import com.example.microfeatures.ui.screens.factories.friends.FriendsUiModelImpl
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactory
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactoryImpl
import com.example.microfeatures.ui.screens.factories.time.TimeUiModel
import com.example.microfeatures.ui.screens.factories.time.TimeUiModelImpl
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactory
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactoryImpl
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModel
import com.example.microfeatures.ui.screens.factories.user.UserDataUiModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FactoriesModule {

    @Binds
    fun bindUserUiFactory(factory: UserDataUiModelImpl.Factory): UserDataUiModel.Factory

    @Binds
    fun bindUserComposableFactory(factory: UserComposableFactoryImpl): UserComposableFactory

    @Binds
    fun bindFriendsUiFactory(factory: FriendsUiModelImpl.Factory): FriendsUiModel.Factory

    @Binds
    fun bindFriendListComposableFactory(factory: FriendListComposableFactoryImpl): FriendListComposableFactory
    @Binds
    fun bindTimeUiFactory(factory: TimeUiModelImpl.Factory): TimeUiModel.Factory

    @Binds
    fun bindTimeComposableFactory(factory: TimeComposableFactoryImpl): TimeComposableFactory
}