package com.example.mytodo.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoModel(
    val title: String,
    val timestamp: String,
    var isChecked: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
