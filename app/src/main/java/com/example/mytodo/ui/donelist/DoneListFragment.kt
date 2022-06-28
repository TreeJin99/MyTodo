package com.example.mytodo.ui.donelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytodo.R

class DoneListFragment: Fragment() {
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
    ): View? {

        // 프레그먼트와 레이아웃 연결된다.
        return inflater.inflate(R.layout.fragment_done_list, container, false)
    }

    companion object{
        const val TAG: String = "로그"

        fun DoneListInstance(): DoneListFragment = DoneListFragment()
    }
}
