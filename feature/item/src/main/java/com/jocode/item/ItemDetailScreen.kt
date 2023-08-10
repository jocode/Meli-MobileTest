package com.jocode.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jocode.components.LottieContent
import com.jocode.item.components.ItemAppBar
import com.jocode.item.navigation.ItemDetailContent
import com.jocode.meli_mobiletest.R

@Composable
fun ItemDetailScreen(
    uiState: ItemDetailUiState,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            ItemAppBar(
                title = stringResource(id = R.string.product),
                onBackPressed = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                ItemDetailUiState.Loading -> {
                    LoadingView()
                }

                is ItemDetailUiState.Success -> {
                    ItemDetailContent(
                        product = uiState.product
                    )
                }

                is ItemDetailUiState.LoadFailed -> {
                    LottieContent(
                        animationRes = R.raw.error,
                        message = uiState.message.asString()
                    )
                }
            }
        }
    }


}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}