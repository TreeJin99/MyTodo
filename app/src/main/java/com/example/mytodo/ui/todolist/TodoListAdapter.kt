//package com.example.mytodo.ui.todolist
//
//import android.annotation.SuppressLint
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.mytodo.databinding.TodoitemsBinding
//import com.example.mytodo.dto.TodoModel
//import com.example.mytodo.ui.donelist.DoneListAdapter.Companion.diffUtil
//import com.example.mytodo.viewmodel.TodoViewModel
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
///**
// * TODO
// * Adapter가 비대해서 크기 줄이기!
// *
// */
//
//class TodoListAdapter(private val todoViewModel: TodoViewModel): ListAdapter<TodoModel, TodoViewHolder>(diffUtil){
//
//}
//
////class TodoListAdapter(private val todoViewModel: TodoViewModel) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
////    private var todoItems: List<TodoModel> = mutableListOf()
////
////    inner class TodoViewHolder(private val todoItemBinding: TodoitemsBinding) : RecyclerView.ViewHolder(todoItemBinding.root) {
////        fun bind(todoModel: TodoModel) {
////            with(todoItemBinding) {
////                todoTitle.text = todoModel.title
////                todoCheckBox.isChecked = todoModel.isChecked
////                todoDate.text = todoModel.endDate
////
////                // 체크 리스너 초기화 해줘 중복 오류 방지
////                todoCheckBox.setOnCheckedChangeListener(null)
////
////                // 체크 시 업데이트
////                todoCheckBox.setOnCheckedChangeListener { _, check ->
////                    val todoItem =
////                        if (check) {
////                            TodoModel(todoModel.title, todoModel.endDate, todoModel.isImportant, true, todoModel.timeStamp)
////                        } else {
////                            TodoModel(todoModel.title, todoModel.endDate, todoModel.isImportant, false, todoModel.timeStamp)
////                        }
////
////                    todoItem.id = todoModel.id
////
////                    CoroutineScope(Dispatchers.IO).launch {
////                        todoViewModel.updateTodo(todoItem)
////                    }
////                }
////
////                deleteButton.setOnClickListener {
////                    CoroutineScope(Dispatchers.IO).launch {
////                        todoViewModel.deleteTodo(todoModel)
////                    }
////                }
////            }
////        }
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
////        val todoItemBinding: TodoitemsBinding = TodoitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
////        return TodoViewHolder(todoItemBinding)
////    }
////
////    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
////        holder.bind(todoItems[position])
////    }
////
////    override fun getItemCount(): Int = todoItems.size
////
////    @SuppressLint("NotifyDataSetChanged")
////    fun update(newItem: List<TodoModel>) {
////        todoItems = newItem
////        notifyDataSetChanged()
////    }
////}
