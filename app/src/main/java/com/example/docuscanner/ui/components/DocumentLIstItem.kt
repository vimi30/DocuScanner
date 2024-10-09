package com.example.docuscanner.ui.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docuscanner.R
import com.example.docuscanner.data.models.DocumentEntity
import com.example.docuscanner.ui.theme.DocuScannerTheme
import com.example.docuscanner.utils.getFileUri
import java.util.Date

@Composable
fun DocumentListItem(
    document: DocumentEntity,
    onRenameClicked: (DocumentEntity) -> Unit,
    onDeleteClicked: (DocumentEntity) -> Unit,
    setCurrentDoc: (DocumentEntity) -> Unit
) {
    val currentDoc by rememberUpdatedState(document)
    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }
    var pressOffSet by remember { mutableStateOf(DpOffset.Zero) }
    var itemHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val activity = context as? Activity
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .indication(interactionSource, LocalIndication.current)
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffSet = DpOffset(it.x.toDp(), it.y.toDp())
                            setCurrentDoc(currentDoc)
                        },
                        onPress = {
                            val pressInteraction = PressInteraction.Press(it)
                            interactionSource.emit(pressInteraction)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(pressInteraction))
                        },
                        onTap = {
                            val fileUri = getFileUri(context, currentDoc.name)
                            val browserIntent = Intent(Intent.ACTION_VIEW, fileUri)
                            browserIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                            activity?.startActivity(browserIntent)
                        }
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.pdf_icon),
                contentDescription = "pdf icon",

                )
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    text = currentDoc.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = currentDoc.size,
                    fontSize = 12.sp,
                )
                Text(
                    text = currentDoc.lastModifiedTime.toString(),
                    fontSize = 8.sp,
                )
            }

            OptionMenu(
                expanded = isContextMenuVisible,
                onDismissRequest = { isContextMenuVisible = false },
                offset = pressOffSet.copy(
                    y = pressOffSet.y - itemHeight
                ),
                onRename = {
                    onRenameClicked(currentDoc)
                    isContextMenuVisible = false
                },
                onDelete = {
                    onDeleteClicked(currentDoc)
                    isContextMenuVisible = false
                },
                onShare = {
                    val fileUri = getFileUri(context, currentDoc.name)
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "application/pdf"
                        putExtra(Intent.EXTRA_STREAM, fileUri)
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share with"))
                    isContextMenuVisible = false
                }
            )
        }

    }
}

@Preview
@Composable
fun DocumentListItemPreview() {
    DocuScannerTheme {
        DocumentListItem(
            DocumentEntity(
                name = "Document one",
                uid = "this_is_the_unique_id",
                size = "10 KB",
                lastModifiedTime = Date()
            ),
            {},
            {},
            {},
        )
    }
}
