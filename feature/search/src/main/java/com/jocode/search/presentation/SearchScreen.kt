package com.jocode.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jocode.common.utils.toLocalCurrency
import com.jocode.components.LottieContent
import com.jocode.components.SearchToolbar
import com.jocode.meli_mobiletest.R
import com.jocode.model.search.Product
import com.jocode.ui.theme.surfaceColor

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchQuerySubmit: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSearchItemClick: (String) -> Unit = {},
    state: SearchResultUiState = SearchResultUiState.Loading,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceColor)
    ) {
        SearchToolbar(
            onBackClick = onBackClick,
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = searchQuery,
            onSearchTriggered = onSearchQuerySubmit
        )

        when (state) {
            SearchResultUiState.EmptyQuery -> {
                LottieContent(
                    animationRes = R.raw.search,
                    message = stringResource(id = R.string.looking_for_something)
                )
            }

            is SearchResultUiState.LoadFailed -> {
                LottieContent(
                    animationRes = R.raw.error,
                    message = state.message.asString()
                )
            }

            SearchResultUiState.Loading -> {
                LoadingView()
            }

            is SearchResultUiState.Success -> {
                SearchContent(
                    items = state.data,
                    onItemClick = onSearchItemClick
                )
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

@Composable
private fun SearchContent(
    modifier: Modifier = Modifier,
    items: List<Product>,
    onItemClick: (String) -> Unit,
) {
    LazyColumn {
        items(items = items) { item ->
            SearchItem(
                modifier = modifier.clickable {
                    onItemClick(item.id)
                },
                product = item
            )
        }
    }
}

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    product: Product,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(Shapes().small)
                .size(100.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = "Gallery Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )
            Text(
                text = product.price.toLocalCurrency(),
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.available_quantity,
                    product.availableQuantity
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    SearchItem(
        product = Product(
            id = "1",
            title = "Figura de accion Funko Pop katete Kid",
            price = 100.0,
            thumbnail = "https://http2.mlstatic.com/D_NQ_NP_2X_932878-MLA45602395500_042021-F.webp",
            availableQuantity = 1,
            soldQuantity = 1,
            condition = "new",
            currencyId = "ARS"
        )
    )
}