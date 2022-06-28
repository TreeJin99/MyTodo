package com.example.mytodo.ui.todolist

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.viewmodel.TodoViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class TodoListFragment: Fragment() {
    private lateinit var todoBinding: FragmentTodoListBinding
    private lateinit var todoListAdapter: TodoListAdapter
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initRecyclerView()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        todoBinding = FragmentTodoListBinding.inflate(inflater, container, false)

        initButton()
        // 프레그먼트와 레이아웃 연결된다.
        return todoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
    }

    override fun onDestroyView() {
        // todoBinding = null

        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initButton() {
        todoBinding.addFab.setOnClickListener {
            Toast.makeText(activity, "테스트", Toast.LENGTH_LONG).show()
            val dateAndTime: LocalDateTime = LocalDateTime.now()
            todoViewModel.createTodo(TodoModel("테스트", dateAndTime.toString(), false))
        }
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
