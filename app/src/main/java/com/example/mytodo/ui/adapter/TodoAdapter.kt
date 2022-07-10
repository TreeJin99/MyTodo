package com.example.mytodo.ui.adapter

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

            with(binding) {
                cbTodoCheck.setOnCheckedChangeListener(null)
                cbTodoCheck.isChecked = todoModel.isChecked

                cbTodoCheck.setOnCheckedChangeListener { _, isChecked ->
                    when(isChecked){
                        true -> {
                            todoModel.isChecked = true
                        }
                        else -> { // == false
                            todoModel.isChecked = false
                        }
                    }

                    CoroutineScope(Dispatchers.IO).launch {
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