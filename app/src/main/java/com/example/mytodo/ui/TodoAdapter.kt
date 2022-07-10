package com.example.mytodo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.databinding.ItemTodoBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoAdapter(private val todoViewModel: TodoViewModel) : ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemTodoBinding: ItemTodoBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutItem, parent, false)

        return TodoViewHolder(itemTodoBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel) {
            binding.itemTodo = todoModel

            Log.d("태그", "호출된 것: ${todoModel}")

            with(binding) {
                cbTodoCheck.setOnCheckedChangeListener { _, isChecked ->
                    todoModel.isChecked = isChecked
                    CoroutineScope(Dispatchers.IO).launch {
                        Log.d("태그", "$todoModel  + ${todoModel.id}")
                        todoViewModel.updateTodo(todoModel)
                    }
                }

                ivTodoDelete.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.deleteTodo(todoModel)
                    }
                }

            }
        }
    }

    companion object {
        const val layoutItem = R.layout.item_todo

        val diffUtil by lazy {
            object : DiffUtil.ItemCallback<TodoModel>() {
                override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean = oldItem.id == newItem.id
                override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean = oldItem == newItem
            }
        }
    }
}