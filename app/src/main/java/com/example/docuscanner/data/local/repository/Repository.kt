package com.example.docuscanner.data.local.repository

import com.example.docuscanner.data.local.dao.DocumentDao
import com.example.docuscanner.data.models.DocumentEntity
import kotlinx.coroutines.flow.Flow

class Repository(
    private val documentDao: DocumentDao
) {
    suspend fun addNewDocument(document: DocumentEntity) {
        documentDao.insertDocument(document)
    }

    suspend fun updateDocument(document: DocumentEntity) {
        documentDao.updateDocument(document)
    }

    suspend fun deleteDocument(document: DocumentEntity) {
        documentDao.deleteDocument(document)
    }

    fun getAllPDFs(): Flow<List<DocumentEntity>> {
        return documentDao.getAllPdfs()
    }

}