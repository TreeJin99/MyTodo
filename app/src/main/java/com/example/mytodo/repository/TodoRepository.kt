package com.example.mytodo.repository

import android.util.Log
import com.example.mytodo.dao.TodoDao
import com.example.mytodo.dto.TodoModel
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    fun searchTodo(todoTitle: String): Flow<List<TodoModel>> = todoDao.searchTodo(todoTitle)

    val readAllTodo: Flow<List<TodoModel>> = todoDao.readAllTodo()

    val readDoneTodo: Flow<List<TodoModel>> = todoDao.readDoneTodo()

    suspend fun createTodo(todoModel: TodoModel) = todoDao.createTodo(todoModel)

    suspend fun updateTodo(todoModel: TodoModel) = todoDao.updateTodo(todoModel)

    suspend fun deleteTodo(todoModel: TodoModel) = todoDao.deleteTodo(todoModel)
}