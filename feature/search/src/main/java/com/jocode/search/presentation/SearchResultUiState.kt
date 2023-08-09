package com.jocode.search.presentation

import com.jocode.model.search.Product

sealed interface SearchResultUiState {
    object Loading : SearchResultUiState
    data class Success(val data: List<Product>) : SearchResultUiState
    object EmptyQuery : SearchResultUiState
    data class LoadFailed(val message: String) : SearchResultUiState
}