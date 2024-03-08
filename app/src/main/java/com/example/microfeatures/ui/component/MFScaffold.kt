/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MFScaffold(
    text: String,
    showBackButton: Boolean = false,
    backAction: () -> Unit = {},
    content: @Composable (() -> Unit)
) {
    Scaffold(
        topBar = {
            TopBar(text = text, showBackButton = showBackButton, backAction = backAction)
        }
    ) { padding ->
        Surface(
            Modifier
                .padding(padding)
                .fillMaxSize()) {
            content()
        }
    }
}