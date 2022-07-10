package com.example.mytodo.ui.donelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoitemsBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoneListAdapter(private val todoViewModel: TodoViewModel) : ListAdapter<TodoModel, DoneListAdapter.DoneItemViewHolder>(diffUtil) {

    inner class DoneItemViewHolder(private val todoItemBinding: TodoitemsBinding) : RecyclerView.ViewHolder(todoItemBinding.root) {
        fun bind(todoModel: TodoModel) {
            with(todoItemBinding) {
                todoTitle.text = todoModel.title
                todoCheckBox.isChecked = todoModel.isChecked
                todoDate.text = todoModel.endDate

                // 체크 리스너 초기화 해줘 중복 오류 방지
                todoCheckBox.setOnCheckedChangeListener(null)

                // 체크 시 업데이트
                todoCheckBox.setOnCheckedChangeListener { _, check ->
                    val todoItem =
                        if (check) {
                            TodoModel(todoModel.title, todoModel.endDate, todoModel.isImportant, true, todoModel.timeStamp)
                        } else {
                            TodoModel(todoModel.title, todoModel.endDate, todoModel.isImportant, false, todoModel.timeStamp)
                        }

                    todoItem.id = todoModel.id

                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.updateTodo(todoItem)
                    }
                }

                deleteButton.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.deleteTodo(todoModel)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneItemViewHolder {
        val todoItemBinding = TodoitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoneItemViewHolder(todoItemBinding)
    }

    override fun onBindViewHolder(holder: DoneItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil by lazy {
            object : DiffUtil.ItemCallback<TodoModel>() {
                override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean = oldItem.id == newItem.id
                override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean = oldItem == newItem
            }
        }
    }
}



