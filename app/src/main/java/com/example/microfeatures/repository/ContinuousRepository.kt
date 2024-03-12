/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.repository

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.microfeatures.datastore.MFDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContinuousRepository @Inject constructor(
    dataStore: MFDataStore
) {

    private val _tickStream : MutableStateFlow<Date> = MutableStateFlow(Date())

    init {
        val lifecycleOwner = ProcessLifecycleOwner.get()
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while(isActive){
                    Log.i("TIME", "Emitting new date")
                    _tickStream.emit(Date())
                    delay(dataStore.getPeriodMs().first())
                }
            }
        }
    }

    fun getTime(): Flow<String> {
//        return _timeStream.map {
//            DateFormat.format("HH:mm:ss dd/MM/yyyy", Date()).toString()
//        }
        return flowOf(DateFormat.format("HH:mm:ss dd/MM/yyyy", Date()).toString())
    }
}