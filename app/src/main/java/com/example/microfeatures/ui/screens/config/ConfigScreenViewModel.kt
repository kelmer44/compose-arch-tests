/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.screens.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.datastore.MFDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigScreenViewModel @Inject constructor(private val datastore: MFDataStore) : ViewModel() {

    val uiState: StateFlow<ConfigState> =
        combine(
            datastore.getPeriodMs(),
            datastore.getQuickDelayMs(),
            datastore.getSlowDelayMs()
        ) { period, quick, slow ->
            ConfigState(
                slow,
                quick,
                period
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ConfigState())

    fun delayChanged(delayType: Delays, value: Float) {
        viewModelScope.launch {
            when (delayType) {
                Delays.Slow -> datastore.setSlowDelayMs(value.toLong())
                Delays.Quick -> datastore.setQuickDelayMs(value.toLong())
                Delays.Period -> datastore.setPeriodMs(value.toLong())
            }
        }
    }

    data class ConfigState(
        val slowDelayMs: Long = 0,
        val quickDelayMs: Long = 0,
        val repeatablePeriod: Long = 1000L
    )

    sealed class Delays(val delayName: String, val range: ClosedFloatingPointRange<Float>, val steps: Int) {
        data object Slow: Delays("Slow delay", 0f .. 5000f, 9)
        data object Quick: Delays("Quick delay", 0f .. 5000f, 9)

        data object Period: Delays("Repeatable period", 1000f .. 5000f, 7)
    }
}