package com.akvelon.reddittest.presentation.common.dialog.progress_bar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.akvelon.reddittest.presentation.common.dialog.RoundCornerDialog

@Composable
fun ProgressBarDialog(
    state: ProgressDialogState
) {
    if (state.isShow) {
        RoundCornerDialog(
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(IndicatorSize),
                    shape = SpinnerShape,
                    color = MaterialTheme.colors.surface,
                    elevation = Elevation,
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val spinnerSize = (ArcRadius + StrokeWidth).times(2)
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            strokeWidth = StrokeWidth,
                            modifier = Modifier.size(spinnerSize),
                        )
                    }
                }
                with(state.text.asString()) {
                    if (isNotEmpty()) {
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = this)
                    }
                }
            }
        }
    }
}

private val IndicatorSize = 40.dp
private val SpinnerShape = CircleShape
private val ArcRadius = 7.5.dp
private val StrokeWidth = 2.5.dp
private val Elevation = 6.dp
