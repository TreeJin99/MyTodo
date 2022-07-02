package com.example.mytodo.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodo.databinding.ActivityEditBinding
import android.app.DatePickerDialog
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var editBind: ActivityEditBinding
    private lateinit var currentType: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editBinding()
        currentType()
        addTodo()
    }

    private fun editBinding() {
        editBind = ActivityEditBinding.inflate(layoutInflater)
        setContentView(editBind.root)
    }

    // 현재 타입 받아오기
    @RequiresApi(Build.VERSION_CODES.O)
    private fun currentType() {
        currentType = intent.getStringExtra("TYPE").toString()
        when (currentType) {
            "ADD" -> {  // 추가
                addTodo()
            }
            "EDIT" -> { // 수정
                editTodo()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addTodo() {
        editBind.addTodoButton.setOnClickListener {
            val todoTitle: String = editBind.addTodoTitle.toString()
            val todoTimeStamp: String = getCurrentTime()


        }

    }

    /**
     *
    val title: String,
    val timestamp: String,
    var endDate: String,
    var isImportant: Boolean,
    var isChecked: Boolean
     *
     */

    private fun editTodo() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(): String{
        val currentTime = LocalDate.now()
        val timeFormat = DateTimeFormatter.ofPattern("yyy년 MM월 dd일 HH시 mm분")

        return currentTime.format(timeFormat)
    }

    private fun getEndDate(){
        editBind.todoDatePicker.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("When is your DeadLine?")
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            val datePicker = builder.build()

        }
    }

}