package com.example.docuscanner.ui.screens

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.docuscanner.R
import com.example.docuscanner.data.models.DocumentEntity
import com.example.docuscanner.ui.components.DialogMode
import com.example.docuscanner.ui.components.DocumentListItem
import com.example.docuscanner.ui.components.RenameDeleteItemDialog
import com.example.docuscanner.ui.components.SimpleToolBar
import com.example.docuscanner.ui.viewmodels.HomeViewModel
import com.example.docuscanner.utils.copyPdfFileToAppDirectory
import com.example.docuscanner.utils.deleteFile
import com.example.docuscanner.utils.formatFileSize
import com.example.docuscanner.utils.renameFile
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    scanner: GmsDocumentScanner
) {

    val showRenameDeleteDialog by homeViewModel.showDialog.collectAsState()
    var dialogMode by remember { mutableStateOf(DialogMode.RENAME) }

    val docs by homeViewModel.documents.collectAsState()

    val currentDocument by homeViewModel.currentDoc.collectAsState()

    var newNameText by remember { mutableStateOf("") }

    val activity = LocalContext.current as Activity

    val context = LocalContext.current

    LaunchedEffect(showRenameDeleteDialog) {
        if (showRenameDeleteDialog) {
            newNameText = currentDocument?.name ?: ""
        }
    }

    if (showRenameDeleteDialog) {
        RenameDeleteItemDialog(
            fileName = newNameText,
            dialogMode = dialogMode,
            newNameText = { newText ->
                newNameText = newText
            },
            onDismissClicked = homeViewModel::dismissDialog,
            onConfirmed = {
                if (dialogMode == DialogMode.DELETE) {
                    currentDocument?.let { pdf ->
                        if (deleteFile(context, pdf.name)) {
                            homeViewModel.deleteDocument(pdf)
                        }
                    }

                } else {
                    currentDocument?.let { currentDoc ->
                        if (!currentDoc.name.equals(newNameText, ignoreCase = true)) {
                            renameFile(
                                context = context,
                                oldName = currentDoc.name,
                                newName = newNameText
                            )
                            val updatedDoc = currentDoc.copy(
                                name = newNameText, lastModifiedTime = Date()
                            )
                            homeViewModel.updateDocument(updatedDoc)
                        }
                    }
                }
            }
        )
    }


    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->

        if (activityResult.resultCode == RESULT_OK) {
            val result = GmsDocumentScanningResult.fromActivityResultIntent(
                activityResult.data
            )

            result?.pdf?.let { pdf ->

                Log.d("PdfName", pdf.uri.lastPathSegment.toString())

                val date = Date()
                val fileName = SimpleDateFormat(
                    "dd-mm-yyyy HH:mm:ss",
                    Locale.getDefault()
                ).format(date) + ".pdf"

                val fileSize = copyPdfFileToAppDirectory(
                    context = context,
                    pdfUri = pdf.uri,
                    destinationFileName = fileName
                )

                val document = DocumentEntity(
                    uid = UUID.randomUUID().toString(),
                    name = fileName,
                    size = formatFileSize(fileSize),
                    lastModifiedTime = date
                )
                homeViewModel.addNewDocument(document)
            }
        }

    }

    LaunchedEffect(key1 = Unit) {
        homeViewModel.fetchAllPdfs()
    }
    Scaffold(
        topBar = {
            SimpleToolBar(title = "Home")
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    scanner.getStartScanIntent(activity)
                        .addOnSuccessListener { intentSender ->
                            scannerLauncher.launch(
                                IntentSenderRequest.Builder(intentSender)
                                    .build()
                            )
                        }
                        .addOnFailureListener {
                            it.printStackTrace()
                        }
                },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.document_scan_icon),
                        contentDescription = "scan button"
                    )
                },
                text = { Text(text = "Scan") },
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(docs) { document ->
                DocumentListItem(
                    document = document,
                    onRenameClicked = {
                        dialogMode = DialogMode.RENAME
                        homeViewModel.showRenameDeleteDialog()
                    },
                    onDeleteClicked = {
                        dialogMode = DialogMode.DELETE
                        homeViewModel.showRenameDeleteDialog()
                    },
                    setCurrentDoc = { currentPdf ->
                        homeViewModel.setCurrentDoc(currentPdf)
                    }
                )
            }
        }
    }
}
