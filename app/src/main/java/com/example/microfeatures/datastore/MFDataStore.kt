/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.datastore

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MFDataStore @Inject constructor(
    @ApplicationContext context: Context
) {

    private val dataStore = PreferenceDataStoreFactory.create(
        null,
        emptyList(),
        produceFile = { context.preferencesDataStoreFile("micro-features-test") }
    )

    fun getQuickDelayMs(): Flow<Long> = dataStore.data.map { preferences ->
        preferences[QUICK_DELAY] ?: 0L
    }.distinctUntilChanged()


    suspend fun setQuickDelayMs(delayMs: Long) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[QUICK_DELAY] = delayMs
        }
    }

    fun getSlowDelayMs() = dataStore.data.map { preferences ->
        preferences[SLOW_DELAY] ?: 0L
    }.distinctUntilChanged()

    suspend fun setSlowDelayMs(delayMs: Long) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[SLOW_DELAY] = delayMs
        }
    }

    fun getPeriodMs()  = dataStore.data.map { preferences ->
        preferences[PERIOD] ?: 1000L
    }.distinctUntilChanged()


    suspend fun setPeriodMs(periodMs: Long) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PERIOD] = periodMs
        }
    }

    private companion object {
        private val SLOW_DELAY = longPreferencesKey("slow_delay")
        private val QUICK_DELAY = longPreferencesKey("quick_delay")
        private val PERIOD = longPreferencesKey("period")
    }
}