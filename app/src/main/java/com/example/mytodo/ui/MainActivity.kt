package com.example.mytodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.mytodo.R
import com.example.mytodo.databinding.ActivityMainBinding
import com.example.mytodo.ui.donelist.DoneListFragment
import com.example.mytodo.ui.setting.SettingFragment
import com.example.mytodo.ui.todolist.TodoListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(){
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var todoListFragment: TodoListFragment
    private lateinit var doneListFragment: DoneListFragment
    private lateinit var settingFragment: SettingFragment
    private lateinit var toolbar: Toolbar
    private var backKeyPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding()
        initToolbar()
        initBottomNav()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backKeyPressed >= BACKPRESSEDTIME) {
            backKeyPressed = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        }else{
            exitProcess(0)
        }
    }

    private fun mainBinding() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initToolbar() {
        toolbar = mBinding.toolbar
        setSupportActionBar(toolbar)
    }


    private fun initBottomNav() {
        /**
         * TODO
         * 바텀 네비게이션 초기에 선택되어 있는 메뉴 바꾸기
         */

        // 프래그먼트 초기 화면 설정
        todoListFragment = TodoListFragment.todoListPageInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, todoListFragment).commit()

        val onBottomNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val currentFragment = supportFragmentManager.beginTransaction()

            when(item.itemId){
                R.id.menu_todoList ->{
                    todoListFragment = TodoListFragment.todoListPageInstance()
                    currentFragment.replace(R.id.fragmentFrame, todoListFragment).commit()
                }
                R.id.menu_doneList ->{
                    doneListFragment = DoneListFragment.doneListInstance()
                    currentFragment.replace(R.id.fragmentFrame, doneListFragment).commit()
                }
                R.id.menu_setting ->{
                    settingFragment = SettingFragment.settingInstance()
                    currentFragment.replace(R.id.fragmentFrame, settingFragment).commit()
                }
            }
            true
        }
        mBinding.bottomNav.setOnItemSelectedListener(onBottomNavigationSelectedListener)
    }

    companion object {
        const val TAG: String = "로그"
        const val BACKPRESSEDTIME: Long = 2000
    }
}

