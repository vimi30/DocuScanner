package com.example.docuscanner.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(128.dp)

@Composable
fun LoadingStateComponent(modifier: Modifier = defaultModifier) {
    CircularProgressIndicator(
        modifier = modifier,
    )
}