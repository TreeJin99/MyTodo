package com.example.mytodo.ui.todolist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoitemsBinding
import com.example.mytodo.dto.TodoModel

class TodoListAdapter(): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    var todoItems: List<TodoModel> = mutableListOf()

    inner class TodoViewHolder(private val todoItemBinding: TodoitemsBinding): RecyclerView.ViewHolder(todoItemBinding.root){
        fun bind(todoModel: TodoModel){
            with(todoItemBinding){
                todoTitle.text = todoModel.title
                todoDate.text = todoModel.timestamp
                isChecked.isChecked = todoModel.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoItemBinding: TodoitemsBinding = TodoitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoViewHolder(todoItemBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int = todoItems.size

    fun update(newItem: List<TodoModel>){
        todoItems = newItem
        notifyDataSetChanged()
    }
}