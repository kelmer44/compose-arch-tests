/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.microfeatures.ui.screens.config.ConfigScreen
import com.example.microfeatures.ui.screens.factories.FactoriesScreen
import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactory
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactory
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactory
import com.example.microfeatures.ui.screens.multiple.MultipleViewModelsScreen
import com.example.microfeatures.ui.screens.regular.RegularArchitectureScreen
import com.example.microfeatures.ui.screens.selectionscreen.SelectionScreen
import com.example.microfeatures.ui.screens.single.SingleViewModelScreen

@Composable
fun MicroFeaturesTestApp(
    appState: AppState = rememberMicroFeaturesAppState(),
    friendListComposableFactory: FriendListComposableFactory,
    userComposableFactory: UserComposableFactory,
    timeComposableFactory: TimeComposableFactory
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
                },
                onSettingsClicked = {
                    appState.navigateToSettings(navBackStackEntry)
                },
                onFactoriesClicked = {
                    appState.navigateToFactories(navBackStackEntry)
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
        composable(Screen.Settings.route) { navBackStackEntry ->
            ConfigScreen(backAction = backAction)
        }
        composable(Screen.Factories.route) { navBackStackEntry ->
            FactoriesScreen(
                friendListComposableFactory = friendListComposableFactory,
                userComposableFactory = userComposableFactory,
                timeComposableFactory = timeComposableFactory,
                backAction = backAction
            )
        }
    }
}