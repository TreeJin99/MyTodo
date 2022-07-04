package com.example.mytodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mytodo.database.TodoDatabase
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.repository.TodoRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * TODO
 * MVVM 기준으로 봤을때 AndroidViewModel은 적절하지 않다.
 * Database에 대한 의존성 주입을 다시 한번 고민해보기
 * Application을 넣어주는 주체가 todoViewModel이 되면 안된다.
 *
 */


class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDatabase(application)!!.todoDao()
    private val todoRepository: TodoRepository = TodoRepository(todoDao)
    val readAllTodo: LiveData<List<TodoModel>> = todoRepository.readAllTodo.asLiveData()

    fun searchTodo(todoTitle: String): LiveData<List<TodoModel>> = todoRepository.searchTodo(todoTitle).asLiveData()

    fun selectOne(id:Long): LiveData<List<TodoModel>> = todoRepository.selectOne(id).asLiveData()

    fun createTodo(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.createTodo(todoModel)
        }
    }

    fun updateTodo(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todoModel)
        }
    }

    fun deleteTodo(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todoModel)
        }
    }
}
