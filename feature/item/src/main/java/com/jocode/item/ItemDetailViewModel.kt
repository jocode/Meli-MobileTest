package com.jocode.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jocode.common.network.getErrorMessage
import com.jocode.domain.GetItemDescriptionUseCase
import com.jocode.meli_mobiletest.R
import com.jocode.model.search.ProductDetail
import com.jocode.models.ItemDetail
import com.jocode.navigation.Screens
import com.jocode.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val useCase: GetItemDescriptionUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var productDetail = MutableStateFlow<ProductDetail?>(null)

    private var _uiState = MutableStateFlow<ItemDetailUiState>(ItemDetailUiState.Loading)
    val uiState: StateFlow<ItemDetailUiState> = _uiState.asStateFlow()

    init {
        try {
            val productId = savedStateHandle.get<String>(Screens.Detail.ITEM_ID_NAV_KEY)
            productId?.let {
                getProductDescription(it)
            }
            observeProductDetail()

        } catch (e: Exception) {
            _uiState.update {
                ItemDetailUiState.LoadFailed(
                    UiText.StringResource(R.string.item_load_failed)
                )
            }
        }


    }

    private fun getProductDescription(productId: String) {
        viewModelScope.launch {
            useCase(productId)
                .onSuccess { data ->
                    productDetail.update {
                        it?.copy(description = data.description)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        ItemDetailUiState.LoadFailed(
                            UiText.StringResource(error.getErrorMessage())
                        )
                    }
                }
        }
    }

    private fun observeProductDetail() {
        viewModelScope.launch {
            productDetail.collect { productDetail ->
                productDetail?.let { item ->
                    _uiState.update {
                        ItemDetailUiState.Success(item)
                    }
                }
            }
        }
    }

    fun onItemDetailReceived(itemDetail: ItemDetail) {
        if (productDetail.value == null) {
            productDetail.update {
                ProductDetail(
                    id = itemDetail.id,
                    title = itemDetail.title,
                    price = itemDetail.price,
                    thumbnail = itemDetail.thumbnail,
                    condition = itemDetail.condition,
                    soldQuantity = itemDetail.soldQuantity,
                    availableQuantity = itemDetail.availableQuantity,
                    currencyId = itemDetail.currencyId,
                    description = ""
                )
            }
        }
    }

}