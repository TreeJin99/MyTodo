package com.example.mytodo.ui.setting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytodo.R

class SettingFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SettingInstance - onCreate() 호출됨")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "SettingInstance - onAttach() 호출됨")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "SettingInstance - onCreateView() 호출됨")

        // 프레그먼트와 레이아웃 연결된다.
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    companion object{
        const val TAG: String = "로그"

        fun settingInstance(): SettingFragment = SettingFragment()
    }
}
