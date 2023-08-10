package com.jocode.models

import android.os.Parcelable
import com.jocode.model.search.Product
import com.jocode.model.search.ProductBase
import kotlinx.parcelize.Parcelize

@Parcelize
class ItemDetail(
    override val id: String,
    override val title: String,
    override val price: Double,
    override val availableQuantity: Int,
    override val soldQuantity: Int,
    override val condition: String,
    override val thumbnail: String,
    override val currencyId: String,
) : ProductBase, Parcelable

fun Product.toParcelable(): ItemDetail {
    return ItemDetail(
        id = id,
        title = title,
        price = price,
        availableQuantity = availableQuantity,
        soldQuantity = soldQuantity,
        condition = condition,
        thumbnail = thumbnail,
        currencyId = currencyId
    )
}