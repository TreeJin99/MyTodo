package com.example.mytodo.ui.donelist

import android.content.Context
import android.content.RestrictionsManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentDoneListBinding
import com.example.mytodo.databinding.FragmentTodoListBinding
import com.example.mytodo.dto.TodoModel
import com.example.mytodo.viewmodel.TodoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class DoneListFragment : Fragment() {
    private var doneBinding: FragmentDoneListBinding? = null
    private lateinit var doneListAdapter: DoneListAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        doneBinding = FragmentDoneListBinding.inflate(inflater, container, false)
        initViewModel()
        initRecyclerView()

        return doneBinding!!.root
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
    }

    private fun initRecyclerView() {
        doneListAdapter = DoneListAdapter(todoViewModel)

        doneBinding!!.doneListRV.apply {
            adapter = doneListAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    todoViewModel.readDoneTodo.collect {
                        doneListAdapter.submitList(it)
                    }
                }

            }
        }

    }

    companion object {
        const val TAG: String = "로그"

        fun doneListInstance(): DoneListFragment = DoneListFragment()
    }
}
