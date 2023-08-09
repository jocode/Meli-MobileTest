package com.jocode.meli_mobiletest.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jocode.navigation.Screens
import com.jocode.search.navigation.searchNavigation

@Composable
fun AppNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        searchNavigation(onNavigateToItemDetail = {
            navController.navigate(Screens.Detail.passItemId(it))
        })

        composable(
            route = Screens.Detail.route,
            arguments = listOf(
                navArgument(name = Screens.Detail.ITEM_ID_NAV_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            Text(text = "Detail Screen ${it.arguments?.getString(Screens.Detail.ITEM_ID_NAV_KEY)}")
        }
    }
}