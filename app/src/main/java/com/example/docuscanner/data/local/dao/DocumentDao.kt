package com.example.docuscanner.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.docuscanner.data.models.DocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(doc: DocumentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDocument(doc: DocumentEntity)

    @Delete
    suspend fun deleteDocument(doc: DocumentEntity)

    @Query("SELECT * FROM documententity")
    fun getAllPdfs() : Flow<List<DocumentEntity>>
}