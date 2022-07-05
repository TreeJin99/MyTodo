package com.example.mytodo.ui.todolist

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.ui.EditActivity
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListFragment : Fragment() {
    private lateinit var todoBinding: FragmentTodoListBinding
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        initViewModel()
        initRecyclerView()
        addTodo()
        isItemChecked()

        // 프레그먼트와 레이아웃 연결된다.
        return todoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
    }

    override fun onDestroyView() {
        // TODO 아래 todoBinding 메모리 누수 방지를 위해 null로 바꿀 수 있는 위치를 찾아서 넣기
        // todoBinding = null

        super.onDestroyView()
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        todoViewModel.readAllTodo.observe(viewLifecycleOwner) {
            todoListAdapter.update(it)
        }
    }

    private fun initRecyclerView() {
        todoListAdapter = TodoListAdapter { todoModel -> deleteTodo(todoModel) }

        todoBinding.todoListRV.apply {
            adapter = todoListAdapter
            setHasFixedSize(true)
        }
        todoListAdapter.setItemCheckBoxClickListener(object: TodoListAdapter.ItemCheckBoxClickListener{
            override fun onClick(view: View, position: Int, itemId: Long) {
                Log.d("태그", "작동됨!~")
            }
        })
    }

    private fun addTodo() {
        todoBinding.addFab.setOnClickListener {
            //Toast.makeText(activity, "테스트", Toast.LENGTH_LONG).show()

            val intentToEdit = Intent(activity, EditActivity::class.java).apply {
                putExtra("TYPE", "ADD")
            }
            startActivity(intentToEdit)
            //startActivityForResult(intentToEdit, ACTIVITY_REQUEST_CODE)
        }
    }

    private fun isItemChecked() {
        todoListAdapter.setItemCheckBoxClickListener(object: TodoListAdapter.ItemCheckBoxClickListener{
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todoItem: LiveData<List<TodoModel>> = todoViewModel.selectOne(itemId)
                }
            }
        })
    }

    private fun deleteTodo(todoModel: TodoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            todoViewModel.deleteTodo(todoModel)
        }
    }


    companion object {
        const val TAG: String = "로그"
        const val ACTIVITY_REQUEST_CODE = 123

        fun todoListPageInstance(): TodoListFragment = TodoListFragment()
    }
}
