/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.microfeatures.ui.multiple.MultipleViewModelsScreen
import com.example.microfeatures.ui.regular.RegularArchitectureScreen
import com.example.microfeatures.ui.selectionscreen.SelectionScreen
import com.example.microfeatures.ui.single.SingleViewModelScreen

@Composable
fun MicroFeaturesTestApp(
    appState: AppState = rememberMicroFeaturesAppState()
) {
    NavHost(
        navController = appState.navHostController,
        startDestination = Screen.Selection.route
    ) {
        composable(Screen.Selection.route) { navBackStackEntry ->
            SelectionScreen(
                onRegularClicked = {
                    appState.navigateToRegular(navBackStackEntry)
                },
                onMultipleViewModelsClicked = {
                    appState.navigateToMultiple(navBackStackEntry)
                },
                onSingleViewModelClicked = {
                    appState.navigateToSingle(navBackStackEntry)
                }
            )
        }
        val backAction = {
            appState.navigateBack()
        }

        composable(Screen.Regular.route) { navBackStackEntry ->
            RegularArchitectureScreen(backAction = backAction)
        }
        composable(Screen.MultipleViewModels.route) { navBackStackEntry ->
            MultipleViewModelsScreen(backAction = backAction)
        }
        composable(Screen.SingleViewModel.route) { navBackStackEntry ->
            SingleViewModelScreen(backAction = backAction)
        }
    }
}