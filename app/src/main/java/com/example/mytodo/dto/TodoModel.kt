package com.example.mytodo.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TodoModel(
    val title: String,
    var endDate: String,
    var isImportant: Boolean,
    var isChecked: Boolean,
    val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
