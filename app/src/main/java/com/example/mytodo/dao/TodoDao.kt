package com.example.mytodo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytodo.dto.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoModel WHERE title LIKE :todoTitle")
    fun searchTodo(todoTitle: String) : Flow<List<TodoModel>>

    @Query("SELECT * FROM TodoModel ORDER BY timestamp")
    fun readAllTodo(): Flow<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createTodo(todoModel: TodoModel)

    @Update
    suspend fun updateTodo(todoModel: TodoModel)

    @Delete
    suspend fun deleteTodo(todoModel: TodoModel)
}