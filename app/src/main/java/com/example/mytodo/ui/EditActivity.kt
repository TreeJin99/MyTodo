package com.example.mytodo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodo.databinding.ActivityEditBinding
import android.app.DatePickerDialog
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var editBind: ActivityEditBinding
    private lateinit var currentType: String
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editBinding()
        initViewModel()
        currentType()
        addTodo()
        getEndDate()    // DatePicker 활성화
    }

    private fun editBinding() {
        editBind = ActivityEditBinding.inflate(layoutInflater)
        setContentView(editBind.root)
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
    }

    // 현재 타입 받아오기
    private fun currentType() {
        currentType = intent.getStringExtra("TYPE").toString()
        when (currentType) {
            "ADD" -> {  // 추가
                addTodo()
            }
            "EDIT" -> { // 수정
            }
        }
    }

    private fun addTodo() {
        editBind.addTodoButton.setOnClickListener {
            val todoTitle: String = editBind.addTodoEditText.text.toString()
            val endDate: String = editBind.todoDatePicker.toString()
            val isImportant: Boolean = editBind.importantSwitch.isChecked
            val timeStamp: String = getCurrentTime()

            Log.d("태그", todoTitle)
            CoroutineScope(Dispatchers.IO).launch {
                todoViewModel.createTodo(TodoModel(todoTitle, endDate, isImportant, false, timeStamp))
            }
        }
    }

    /**
     *
    val title: String,
    var endDate: String,
    var isImportant: Boolean,
    var isChecked: Boolean
     *
     */

    private fun getEndDate() {
        editBind.todoDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val endDate = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                    editBind.todoDatePicker.text = endDate
                }
            DatePickerDialog(this, dateSetListener, year, month, day).show()
        }
    }

    private fun getCurrentTime() = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()).toString()
}
