package com.example.docuscanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.docuscanner.data.local.dao.DocumentDao
import com.example.docuscanner.data.models.DocumentEntity
import com.example.docuscanner.utils.DataTypeConverter

@Database(entities = [DocumentEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun documentDao(): DocumentDao
}