package com.example.mytodo.repository

import androidx.room.*
import com.example.mytodo.dao.TodoDao
import com.example.mytodo.dto.TodoModel
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    fun searchTodo(todoTitle: String): Flow<List<TodoModel>> = todoDao.searchTodo(todoTitle)

    val readAllTodo: Flow<List<TodoModel>> = todoDao.readAllTodo()

    suspend fun createTodo(todoModel: TodoModel) = todoDao.createTodo(todoModel)

    suspend fun updateTodo(todoModel: TodoModel) = todoDao.updateTodo(todoModel)

    suspend fun deleteTodo(todoModel: TodoModel) = todoDao.deleteTodo(todoModel)
}