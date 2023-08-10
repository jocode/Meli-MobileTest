package com.jocode.search.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.jocode.model.search.Product
import com.jocode.navigation.Screens
import com.jocode.search.SearchScreen
import com.jocode.search.SearchViewModel

fun NavGraphBuilder.searchNavigation(
    onNavigateToItemDetail: (Product) -> Unit,
) {
    composable(route = Screens.Search.route) {

        val searchViewModel: SearchViewModel = hiltViewModel()
        val searchQuery by searchViewModel.searchQuery
        val uiState by searchViewModel.uiState.collectAsState()
        val searchResults =
            searchViewModel.searchResults.collectAsState().value.collectAsLazyPagingItems()

        SearchScreen(
            searchQuery = searchQuery,
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchQuerySubmit = searchViewModel::onSearchQuerySubmit,
            onSearchItemClick = onNavigateToItemDetail,
            state = uiState,
            searchResults = searchResults,
            onBackClick = {}
        )
    }
}