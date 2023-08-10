package com.jocode.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jocode.domain.GetSearchItemUseCase
import com.jocode.model.search.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchItemUseCase: GetSearchItemUseCase,
) : ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    private var _uiState = MutableStateFlow<SearchResultUiState>(SearchResultUiState.EmptyQuery)
    val uiState: StateFlow<SearchResultUiState> = _uiState.asStateFlow()

    val searchResults = MutableStateFlow(flowOf(PagingData.empty<Product>()))

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onSearchQuerySubmit(query: String) {
        _uiState.update { SearchResultUiState.Loading }
        searchResults.update {
            getSearchItemUseCase(query).cachedIn(viewModelScope)
        }
        _uiState.update { SearchResultUiState.Loaded }
    }
}