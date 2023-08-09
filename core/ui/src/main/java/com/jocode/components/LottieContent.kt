package com.jocode.components

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jocode.ui.theme.surfaceColor

@Composable
fun LottieContent(
    modifier: Modifier = Modifier,
    @RawRes animationRes: Int,
    message: String = "",
) {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationRes)
    )
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceColor)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = message,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface
        )
        LottieAnimation(
            composition = lottieComposition,
            modifier = Modifier.size(250.dp),
            iterations = LottieConstants.IterateForever
        )

    }
}