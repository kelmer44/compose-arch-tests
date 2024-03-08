/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

object Delays {
    val immediate: Duration = 0.milliseconds
    val quick: Duration = 2.seconds
    val slow: Duration = 5.seconds
}