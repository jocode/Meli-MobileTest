package com.jocode.search.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jocode.common.result.Result
import com.jocode.common.result.asResult
import com.jocode.domain.GetSearchItemUseCase
import com.jocode.network.common.getErrorMessage
import com.jocode.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchItemUseCase: GetSearchItemUseCase,
) : ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    private var _uiState = MutableStateFlow<SearchResultUiState>(SearchResultUiState.EmptyQuery)
    val uiState: StateFlow<SearchResultUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onSearchQuerySubmit(query: String) {
        _uiState.update { SearchResultUiState.Loading }
        viewModelScope.launch {
            getSearchItemUseCase(query)
                .onSuccess {
                    it.asResult().collect { result ->
                        println(result)
                        _uiState.update {
                            when (result) {
                                is Result.Success -> {
                                    SearchResultUiState.Success(result.data)
                                }

                                is Result.Error -> {
                                    val message = result.exception.getErrorMessage()
                                    Timber.e("Error: $message")
                                    SearchResultUiState.LoadFailed(UiText.StringResource(message))
                                }

                                is Result.Loading -> {
                                    SearchResultUiState.Loading
                                }
                            }
                        }
                    }
                }
                .onFailure { error ->
                    Timber.e("Error: $error")
                    _uiState.update {
                        SearchResultUiState.LoadFailed(
                            UiText.StringResource(error.getErrorMessage())
                        )
                    }
                }
        }

    }

}