package com.example.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import com.example.mytodo.databinding.ActivityMainBinding
import com.example.mytodo.ui.donelist.DoneListFragment
import com.example.mytodo.ui.setting.SettingFragment
import com.example.mytodo.ui.todolist.TodoListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var todoListFragment: TodoListFragment
    private lateinit var doneListFragment: DoneListFragment
    private lateinit var settingFragment: SettingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initMainBinding()
        initBottomNav()
    }

    private fun initMainBinding() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initBottomNav() {
         val onBottomNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_todoList ->{
                    Log.d(TAG, "Clicked TODOLIST")
                    todoListFragment = TodoListFragment.TodoListPageInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, todoListFragment).commit()
                }
                R.id.menu_doneList ->{
                    Log.d(TAG, "Clicked DoneList")
                    doneListFragment = DoneListFragment.DoneListInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, doneListFragment).commit()
                }
                R.id.menu_setting ->{
                    Log.d(TAG, "Clicked Setting")
                    settingFragment = SettingFragment.SettingInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, settingFragment).commit()
                }
            }
            true
        }

        mBinding.bottomNav.setOnItemSelectedListener(onBottomNavigationSelectedListener)
    }

    companion object {
        const val TAG: String = "로그"
    }
}

