/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.di

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun imageLoader(
        @ApplicationContext application: Context,
    ): ImageLoader =
        ImageLoader.Builder(application)
            .apply {
                logger(DebugLogger())
            }
            .build()

}