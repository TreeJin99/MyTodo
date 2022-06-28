package com.example.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodo.dao.TodoDao
import com.example.mytodo.dto.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        /**
         * @Volatile = 접근가능한 변수의 값을 Cache 통해 사용하지 않고
         * Thread가 직접 Main Memory에 접근하도록 동기화한다.
         */
        @Volatile
        private var TODO_INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase?{
            if(TODO_INSTANCE == null){
                synchronized(TodoDatabase::class){
                    TODO_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "Todo_Database"
                    ).build()
                }
            }
            return TODO_INSTANCE
        }
    }
}