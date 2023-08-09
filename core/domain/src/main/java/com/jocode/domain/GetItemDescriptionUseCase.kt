package com.jocode.domain

import com.example.items_search.ItemRepository
import javax.inject.Inject

class GetItemDescriptionUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    suspend operator fun invoke(productId: String) =
        itemRepository.getProductDescription(productId)
}