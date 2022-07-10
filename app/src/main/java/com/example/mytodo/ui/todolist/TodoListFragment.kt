package com.example.mytodo.ui.todolist

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.ui.TodoAdapter
import com.example.mytodo.ui.EditActivity
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoListFragment : Fragment() {
    private var todoBinding: FragmentTodoListBinding? = null
    private lateinit var todoAdapter: TodoAdapter

    private val todoViewModel: TodoViewModel by lazy{
        ViewModelProvider(this)[TodoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        todoBinding = FragmentTodoListBinding.inflate(inflater, container, false)

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
        todoAdapter = TodoAdapter(todoViewModel)

        todoBinding!!.todoListRV.apply {
            adapter = todoAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    todoViewModel.readAllTodo.collect{
                        todoAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun addTodo() {
        todoBinding!!.addFab.setOnClickListener {
            Toast.makeText(activity, "테스트", Toast.LENGTH_LONG).show()
            todoViewModel.createTodo(TodoModel("하하", "하하", false, false, "1234"))

            val intentToEdit = Intent(activity, EditActivity::class.java).apply {
                putExtra("TYPE", "ADD")
            }
            startActivity(intentToEdit)
        }
    }

    companion object {
        const val TAG: String = "로그"
        const val ACTIVITY_REQUEST_CODE = 123

        fun todoListPageInstance(): TodoListFragment = TodoListFragment()
    }
}
