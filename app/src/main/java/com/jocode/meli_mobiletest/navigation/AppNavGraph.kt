package com.jocode.meli_mobiletest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jocode.search.presentation.navigation.searchNavigation

@Composable
fun AppNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        searchNavigation()
    }
}