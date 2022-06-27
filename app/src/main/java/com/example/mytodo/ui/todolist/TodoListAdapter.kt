package com.example.mytodo.ui.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.dto.TodoModel

class TodoListAdapter: RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    var todoItems: List<TodoModel> = mutableListOf()

    inner class TodoViewHolder(todoItemBinding: FragmentTodoListBinding): RecyclerView.ViewHolder(todoItemBinding.root){
        fun bind(todoModel: TodoModel){
            with(todoModel){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoItemBinding: FragmentTodoListBinding = FragmentTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(todoItemBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int = todoItems.size
}