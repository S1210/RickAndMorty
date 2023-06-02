package com.akvelon.reddittest.presentation.common.component

import androidx.annotation.StringRes
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Headline(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = 0,
    text: String = "",
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = FontWeight.Bold,
    color: Color = LocalContentColor.current,
    textAlign: TextAlign? = null,
) {
    Text(
        modifier = modifier,
        text = if (textRes != 0) stringResource(id = textRes) else text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}
