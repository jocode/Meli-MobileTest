package com.jocode.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jocode.ui.theme.OnSecondaryButton
import com.jocode.ui.theme.PrimaryButton
import com.jocode.ui.theme.SecondaryButton

@Composable
fun ButtonDefault(
    modifier: Modifier = Modifier,
    text: String,
    buttonStyle: ButtonStyle = ButtonStyle.Primary,
    onClick: () -> Unit,
) {
    val backgroundColor = when (buttonStyle) {
        ButtonStyle.Primary -> Color.PrimaryButton
        ButtonStyle.Secondary -> Color.SecondaryButton
    }

    val textColor = when (buttonStyle) {
        ButtonStyle.Primary -> Color.White
        ButtonStyle.Secondary -> Color.OnSecondaryButton
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 12f)
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 36.dp, vertical = 12.dp)
                .align(Alignment.Center),
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.Normal
        )
    }

}

@Preview
@Composable
fun ButtonDefaultPreview() {
    ButtonDefault(
        text = "Button",
        onClick = {}
    )
}

@Preview
@Composable
fun ButtonDefaultSecondaryPreview() {
    ButtonDefault(
        text = "Button",
        onClick = {},
        buttonStyle = ButtonStyle.Secondary
    )
}

enum class ButtonStyle {
    Primary,
    Secondary
}