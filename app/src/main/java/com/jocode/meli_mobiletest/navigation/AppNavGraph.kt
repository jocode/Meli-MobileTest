package com.jocode.meli_mobiletest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jocode.item.navigation.itemDetailNavigation
import com.jocode.models.toParcelable
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
        searchNavigation(
            onNavigateToItemDetail = { item ->
                // Pass the item to the detail screen
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Screens.Detail.ITEM_DETAIL_KEY,
                    item.toParcelable()
                )

                navController.navigate(
                    Screens.Detail.passItemId(
                        productId = item.id
                    )
                )
            })

        itemDetailNavigation(
            navController = navController,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}