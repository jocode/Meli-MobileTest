package com.jocode.search

import com.jocode.utils.UiText

sealed interface SearchResultUiState {
    object Loading : SearchResultUiState
    object EmptyQuery : SearchResultUiState
    object Loaded : SearchResultUiState

    data class LoadFailed(val message: UiText) : SearchResultUiState
}