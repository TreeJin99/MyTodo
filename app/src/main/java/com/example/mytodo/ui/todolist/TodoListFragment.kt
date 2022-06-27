package com.example.mytodo.ui.todolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTodoListBinding

class TodoListFragment: Fragment() {
    private lateinit var todoBinding: FragmentTodoListBinding
    private lateinit var todoListAdapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "TodoListFragment - onCreate() 호출됨")

        initRecyclerView()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "TodoListFragment - onAttach() 호출됨")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "TodoListFragment - onCreateView() 호출됨")
        todoBinding = FragmentTodoListBinding.inflate(inflater, container, false)
        // 프레그먼트와 레이아웃 연결된다.
        return todoBinding.root
    }

    private fun initRecyclerView(){
        todoListAdapter = TodoListAdapter()
        todoBinding.todoListRV.apply {
            setHasFixedSize(true)
            adapter = todoListAdapter
        }
    }

    companion object{
        const val TAG: String = "로그"

        fun TodoListPageInstance(): TodoListFragment = TodoListFragment()
    }
}
