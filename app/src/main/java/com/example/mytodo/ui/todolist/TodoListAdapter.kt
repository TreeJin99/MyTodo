package com.example.mytodo.ui.todolist

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.databinding.TodoitemsBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.ui.MainActivity

/**
 * TODO
 * Adapter가 비대해서 크기 줄이기!
 *
 */

class TodoListAdapter(private val deleteItem: (TodoModel) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    private var todoItems: List<TodoModel> = mutableListOf()
    private lateinit var itemCheckBoxClickListener: ItemCheckBoxClickListener
        
    // TODO inner class를 nested Class로 변환하기
    inner class TodoViewHolder(private val todoItemBinding: TodoitemsBinding) :
        RecyclerView.ViewHolder(todoItemBinding.root) {
        fun bind(todoModel: TodoModel) {
            with(todoItemBinding) {
                todoTitle.text = todoModel.title
                isChecked.isChecked = todoModel.isChecked

                /*
                if(isChecked.isChecked)
                    todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
                else
                    todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                 */

                isChecked.setOnClickListener {
                    itemCheckBoxClickListener.onClick(it, layoutPosition, todoItems.id)
                }
                deleteButton.setOnClickListener {
                    deleteItem(todoModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        //val todoDataBinding: TodoitemsBinding = DataBindingUtil.

        val todoItemBinding: TodoitemsBinding =
            TodoitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoViewHolder(todoItemBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int = todoItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newItem: List<TodoModel>) {
        todoItems = newItem
        notifyDataSetChanged()
        Log.d("태그", "호출되었습니다!")
    }

    interface ItemCheckBoxClickListener{
        fun onClick(view: View, position: Int, itemId: Long)
    }

    fun setItemCheckBoxClickListener(itemCheckBoxClickListener: ItemCheckBoxClickListener){
        this.itemCheckBoxClickListener = itemCheckBoxClickListener
    }
}
