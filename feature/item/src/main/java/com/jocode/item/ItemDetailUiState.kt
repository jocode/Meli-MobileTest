package com.jocode.item

import com.jocode.model.search.ProductDetail
import com.jocode.utils.UiText

sealed interface ItemDetailUiState {
    object Loading : ItemDetailUiState
    data class Success(val product: ProductDetail) : ItemDetailUiState
    data class LoadFailed(val message: UiText) : ItemDetailUiState
}