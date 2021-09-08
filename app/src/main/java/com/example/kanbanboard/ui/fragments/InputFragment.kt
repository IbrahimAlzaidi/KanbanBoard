package com.example.kanbanboard.ui.fragments

import android.content.ContentValues
import android.view.LayoutInflater
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.data.DbSchema
import com.example.kanbanboard.databinding.FragmentInputBinding

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
            val state = binding?.taskType?.selectedItem.toString().trim()
            //val taskDate = binding?.editTextDate?.text.toString()
            val newEntry = ContentValues().apply {
                put(DbSchema.TASK_TITLE, title)
                put(DbSchema.TASK_DESC, desc)
                put(DbSchema.TASK_STATS, state)
            }
            dbHelper.writableDatabase.insert(DbSchema.TABLE_TASKS, null, newEntry)
        }
    }
}