package com.example.docuscanner.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import com.example.docuscanner.R

@Composable
fun OptionMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    offset: DpOffset,
    onRename: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    ) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = offset
    ) {
        DropdownMenuItem(
            onClick = onRename,
            text = { Text(text = "Rename") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.edit_icon),
                    contentDescription = "share"
                )
            }
        )

        DropdownMenuItem(
            onClick = onDelete,
            text = { Text(text = "Delete") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.delete_icon),
                    contentDescription = "share"
                )
            }
        )

        DropdownMenuItem(
            onClick = onShare,
            text = { Text(text = "Share") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.share_icon),
                    contentDescription = "share"
                )
            }
        )
    }
}

@Preview
@Composable
fun OptionMenuPreview() {
    OptionMenu(
        expanded = true,
        offset = DpOffset.Zero,
        onDismissRequest = {},
        onDelete = {},
        onRename = {},
        onShare = {}
    )
}