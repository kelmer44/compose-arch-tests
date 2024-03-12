/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    data object Selection : Screen("selection")
    data object Regular : Screen("regular")

    data object MultipleViewModels : Screen("multiple")

    data object Factories: Screen("factories")
    data object SingleViewModel : Screen("single")
    data object Settings : Screen("settings")
}

@Composable
fun rememberMicroFeaturesAppState(
    navHostController: NavHostController = rememberNavController(),
) = remember(navHostController) {
    AppState(navHostController)
}

class AppState(
    val navHostController: NavHostController,
) {

    fun navigateToRegular(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navHostController.navigate(Screen.Regular.route)
        }
    }

    fun navigateToMultiple(from: NavBackStackEntry) {
        if(from.lifecycleIsResumed()){
            navHostController.navigate(Screen.MultipleViewModels.route)
        }
    }

    fun navigateToSingle(from: NavBackStackEntry) {
        if(from.lifecycleIsResumed()){
            navHostController.navigate(Screen.SingleViewModel.route)
        }
    }

    fun navigateToSettings(from: NavBackStackEntry) {
        if(from.lifecycleIsResumed()){
            navHostController.navigate(Screen.Settings.route)
        }
    }

    fun navigateToFactories(from: NavBackStackEntry) {
        if(from.lifecycleIsResumed()){
            navHostController.navigate(Screen.Factories.route)
        }
    }
    fun navigateBack(){
        navHostController.navigateUp()
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
