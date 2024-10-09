package com.example.docuscanner.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.docuscanner.utils.DataTypeConverter
import java.util.Date

@Entity
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uid: String,
    val name: String,
    val size: String,
    @TypeConverters(DataTypeConverter::class)
    val lastModifiedTime: Date
)
