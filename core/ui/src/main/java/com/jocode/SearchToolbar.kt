package com.jocode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jocode.meli_mobiletest.R
import com.jocode.ui.theme.MeliMobileTestTheme
import com.jocode.ui.theme.brandColor

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    allowBackNavigation: Boolean = false,
    onBackClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.brandColor),
    ) {
        if (allowBackNavigation) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.back,
                    ),
                )
            }
        }
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: (String) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
        focus.clearFocus()
    }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                        focus.clearFocus()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if (!it.contains("\n")) {
                onSearchQueryChanged(it)
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = MaterialTheme.colorScheme.outline,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Preview
@Composable
private fun SearchToolbarPreview() {
    MeliMobileTestTheme {
        SearchToolbar(
            onBackClick = {},
            onSearchQueryChanged = {},
            onSearchTriggered = {},
        )
    }
}