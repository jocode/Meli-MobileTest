package com.jocode.item.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jocode.item.ItemDetailScreen
import com.jocode.item.ItemDetailViewModel
import com.jocode.models.ItemDetail
import com.jocode.navigation.Screens

fun NavGraphBuilder.itemDetailNavigation(
    navController: NavController,
    onNavigateBack: () -> Unit,
) {

    composable(
        route = Screens.Detail.route,
        arguments = listOf(
            navArgument(name = Screens.Detail.ITEM_ID_NAV_KEY) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {

        val itemDetail = navController.previousBackStackEntry?.savedStateHandle?.get<ItemDetail>(
            Screens.Detail.ITEM_DETAIL_KEY
        )
        val viewModel: ItemDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = itemDetail) {
            itemDetail?.let { viewModel.onItemDetailReceived(it) }
        }

        ItemDetailScreen(
            uiState = uiState,
            onNavigateBack = onNavigateBack
        )
    }
}