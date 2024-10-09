package com.example.docuscanner.di

import android.content.Context
import androidx.room.Room
import com.example.docuscanner.data.local.LocalDatabase
import com.example.docuscanner.data.local.dao.DocumentDao
import com.example.docuscanner.data.local.repository.Repository
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "local_document_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDocumentDao(documentDatabase: LocalDatabase): DocumentDao =
        documentDatabase.documentDao()

    @Provides
    @Singleton
    fun provideRepository(
        documentDao: DocumentDao,
    ): Repository = Repository(documentDao)

    @Provides
    @Singleton
    fun provideScannerOptions(): GmsDocumentScannerOptions {
        return GmsDocumentScannerOptions.Builder()
            .setScannerMode(SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
            .build()
    }

    @Provides
    @Singleton
    fun provideScannerClient( scannerOptions: GmsDocumentScannerOptions): GmsDocumentScanner {
        return GmsDocumentScanning.getClient(scannerOptions)
    }

}