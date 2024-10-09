package com.example.docuscanner.ui.components

import android.view.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.docuscanner.R
import com.example.docuscanner.ui.theme.DocuScannerTheme

@Composable
fun RenameDeleteItemDialog(
    fileName: String,
    newNameText: (String) -> Unit,
    dialogMode: DialogMode,
    onDismissClicked: () -> Unit,
    onConfirmed: () -> Unit
) {

    Dialog(
        onDismissRequest = { onDismissClicked() },
    ) {
//        val dialogWindow = getDialogWindow()
//
//        SideEffect {
//            dialogWindow.let { window ->
//                window?.setDimAmount(0f)
//                window?.setWindowAnimations(-1)
//            }
//        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(
                        if (dialogMode == DialogMode.DELETE) {
                            R.drawable.warning_icon
                        } else {
                            R.drawable.edit_icon
                        }

                    ),
                    contentDescription = "delete icon",
                    tint = if (dialogMode == DialogMode.DELETE) Color.Red else Color.Gray)

                Text(
                    text = if (dialogMode == DialogMode.DELETE) "Delete file" else "Rename",
                    style = MaterialTheme.typography.headlineSmall
                )
                if(dialogMode == DialogMode.DELETE){
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Are you sure you want to delete this file? This action can not be undone.",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = fileName,
                    onValueChange = newNameText,
                    label = { Text(text = "PDF Name") },
                    readOnly = dialogMode == DialogMode.DELETE
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                ) {
                    Button(
                        onClick = { onDismissClicked() }
                    ) { Text(text = "Cancel") }

                    Button(
                        onClick = {
                            onConfirmed()
                            onDismissClicked()
                        }
                    ) {
                        Text(text = if(dialogMode == DialogMode.DELETE) "Yes" else "Confirm")
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

}

//@ReadOnlyComposable
//@Composable
//fun getDialogWindow(): Window? = (LocalView.current.parent as? DialogWindowProvider)?.window


enum class DialogMode {
    RENAME,
    DELETE
}

@Preview
@Composable
fun DeleteItemDialogPreview() {
    DocuScannerTheme {
        RenameDeleteItemDialog(
            fileName = "File One",
            dialogMode = DialogMode.DELETE,
            onDismissClicked = {},
            onConfirmed = {},
            newNameText = { }
        )
    }
}

@Preview
@Composable
fun RenameItemDialogPreview() {
    DocuScannerTheme {
        RenameDeleteItemDialog(
            fileName = "File One",
            dialogMode = DialogMode.RENAME,
            onDismissClicked = {},
            onConfirmed = {},
            newNameText = { }
        )
    }
}