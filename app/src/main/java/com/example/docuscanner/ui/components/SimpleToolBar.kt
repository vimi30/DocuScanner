package com.example.docuscanner.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docuscanner.ui.theme.DocuScannerTheme

@Composable
fun SimpleToolBar(
    title: String,
) {
    ElevatedCard(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 12.dp,
            bottomEnd = 12.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 4.dp)
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }

}

@Preview
@Composable
fun SimpleToolBarPreview() {
    DocuScannerTheme {
        SimpleToolBar("Home")
    }
}