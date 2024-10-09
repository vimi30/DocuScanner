package com.example.docuscanner.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import kotlin.math.log10
import kotlin.math.pow
import kotlin.text.*

fun copyPdfFileToAppDirectory(context: Context, pdfUri: Uri, destinationFileName: String): Long {

    val inputStream = context.contentResolver.openInputStream(pdfUri)
    val outputFile = File(context.filesDir, destinationFileName)
    FileOutputStream(outputFile).use {
        inputStream?.copyTo(it)
    }
    return outputFile.length()
}

fun getFileUri(context: Context, fileName: String): Uri {
    val file = File(context.filesDir, fileName)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}

fun renameFile(context: Context, oldName: String, newName: String): Boolean {
    val oldFile = File(context.filesDir, oldName)
    val newFile = File(context.filesDir, newName)
    return oldFile.renameTo(newFile)
}

fun deleteFile(context: Context, fileName: String): Boolean {
    val file = File(context.filesDir, fileName)
    return file.deleteRecursively()
}

@SuppressLint("DefaultLocale")
fun formatFileSize(bytes: Long): String {
    if (bytes <= 0) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(bytes.toDouble()) / log10(1024.0)).toInt()
    val sizeInUnit = bytes / 1024.0.pow(digitGroups.toDouble())

    return String.format("%.2f %s", sizeInUnit, units[digitGroups])
}