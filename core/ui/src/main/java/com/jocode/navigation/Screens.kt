package com.jocode.navigation

sealed class Screens(val route: String) {
    object Search : Screens(route = "search_screen")
    object Detail : Screens(route = "detail_screen?productId={productId}") {
        const val ITEM_ID_NAV_KEY = "productId"
        fun passProductId(productId: String): String {
            return route.replace("{productId}", productId)
        }
    }
}
