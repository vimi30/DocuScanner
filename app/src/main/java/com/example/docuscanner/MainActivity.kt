package com.example.docuscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.docuscanner.ui.screens.HomeScreen
import com.example.docuscanner.ui.theme.DocuScannerTheme
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var documentScanner: GmsDocumentScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocuScannerTheme {
                HomeScreen(
                    scanner = documentScanner
                )
            }
        }
    }
}