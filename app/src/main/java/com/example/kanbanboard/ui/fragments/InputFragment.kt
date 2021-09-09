package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.FragmentInputBinding
import kotlin.random.Random

class InputFragment : BaseFragment<FragmentInputBinding>(){
    override val LOG_TAG: String
        get() = "InputFragment"
    private lateinit var dbHelper: DbHelper
    override val bindingInflater: (LayoutInflater) -> FragmentInputBinding
        get() = FragmentInputBinding :: inflate
    override fun setup() {
        dbHelper = DbHelper(activity?.applicationContext!!)

    }

    override fun addCallBack() {
        binding?.saveButton?.setOnClickListener {
            val title = binding?.textTitle?.text.toString().trim()
            val desc = binding?.taskDescription?.text.toString().trim()
            val state = "TODO"
            dbHelper.addTask(title,desc,state,"Done",Random.nextInt(0,100),"Noor")
        }
    }
}
