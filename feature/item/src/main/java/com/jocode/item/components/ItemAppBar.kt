package com.jocode.item.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.jocode.ui.theme.brandColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemAppBar(
    title: String = "",
    onBackPressed: () -> Unit,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow Icon"
                )
            }
        },
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.brandColor,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}