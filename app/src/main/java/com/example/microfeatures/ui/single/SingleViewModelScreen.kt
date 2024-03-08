/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.single

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.microfeatures.ui.component.MFScaffold

@Composable
fun SingleViewModelScreen(
    viewModel: SingleViewModelViewModel = hiltViewModel(),
    backAction: () -> Unit
) {
    MFScaffold("Single ViewModels", true, backAction = backAction) {
        Text(text = "Not yet implemented")
    }
}