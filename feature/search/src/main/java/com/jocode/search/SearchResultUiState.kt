package com.jocode.search

sealed interface SearchResultUiState {
    object Loading : SearchResultUiState
    object EmptyQuery : SearchResultUiState
    object Loaded : SearchResultUiState

}