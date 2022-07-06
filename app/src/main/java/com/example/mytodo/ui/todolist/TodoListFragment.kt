package com.example.mytodo.ui.todolist

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.ui.EditActivity
import com.example.mytodo.viewmodel.TodoViewModel

class TodoListFragment : Fragment() {
    //private lateinit var todoBinding: FragmentTodoListBinding
    private var todoBinding: FragmentTodoListBinding? = null

    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d("태그", "생성!")
        todoBinding = FragmentTodoListBinding.inflate(inflater, container, false)

        initViewModel()
        initRecyclerView()
        addTodo()

        // 프레그먼트와 레이아웃 연결된다.
        return todoBinding!!.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        todoBinding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        todoListAdapter = TodoListAdapter(todoViewModel)

        todoBinding!!.todoListRV.apply {
            adapter = todoListAdapter
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        todoViewModel.readAllTodo.observe(viewLifecycleOwner) {
            todoListAdapter.update(it)
        }
    }

    private fun addTodo() {
        todoBinding!!.addFab.setOnClickListener {
            //Toast.makeText(activity, "테스트", Toast.LENGTH_LONG).show()

            val intentToEdit = Intent(activity, EditActivity::class.java).apply {
                putExtra("TYPE", "ADD")
            }
            startActivity(intentToEdit)
            //startActivityForResult(intentToEdit, ACTIVITY_REQUEST_CODE)
        }
    }
/*
    private fun deleteTodo(todoModel: TodoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            todoViewModel.deleteTodo(todoModel)
        }
    }
*/

    companion object {
        const val TAG: String = "로그"
        const val ACTIVITY_REQUEST_CODE = 123

        fun todoListPageInstance(): TodoListFragment = TodoListFragment()
    }
}
