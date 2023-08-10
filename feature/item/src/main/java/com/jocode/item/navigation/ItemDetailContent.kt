package com.jocode.item.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jocode.common.utils.toLocalCurrency
import com.jocode.meli_mobiletest.R
import com.jocode.model.search.ProductDetail
import com.jocode.ui.components.ButtonDefault
import com.jocode.ui.components.ButtonStyle

@Composable
fun ItemDetailContent(
    product: ProductDetail,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = stringResource(
                    id = R.string.sold_by,
                    product.condition.localizeCondition(),
                    product.soldQuantity
                ),
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Text(
            text = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(
            text = product.price.toLocalCurrency(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )

        ButtonDefault(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.buy_now),
            onClick = {}
        )

        ButtonDefault(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.add_to_cart),
            onClick = {},
            buttonStyle = ButtonStyle.Secondary
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = product.description,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun String.localizeCondition(): String {
    return when (this) {
        "new" -> stringResource(id = R.string.new_product)
        "used" -> stringResource(id = R.string.used_product)
        else -> ""
    }
}