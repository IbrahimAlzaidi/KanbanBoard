package com.example.kanbanboard.ui.fragments

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
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

    @SuppressLint("SimpleDateFormat")
    override fun addCallBack() {
        binding?.saveButton?.setOnClickListener {
            val data = dbHelper.getAllTasksData()
            val title = binding?.textTitle?.text.toString().trim()
            val desc = binding?.taskDescription?.text.toString().trim()
            val state = binding?.taskState?.selectedItem.toString().trim()
            val type = binding?.taskType?.selectedItem.toString().trim()
            val date = SimpleDateFormat("yyyy/MM/dd HH:mm")
            println("The date is : $date")
            var flag = false

                if(title.isEmpty()||desc.isEmpty()){
                    Toast.makeText(requireActivity().applicationContext,
                        "The Column title couldn't be empty",Toast.LENGTH_LONG).show()
                    flag = true
                }
                else  {
                    flag = false
                    for (i in 0 until data.size) {
                        if (title == data[i].titleTask || desc == data[i].descTask) {
                            Toast.makeText(
                                requireActivity().applicationContext,
                                "This Task is Already Existing", Toast.LENGTH_LONG
                            ).show()
                            flag = true
                            break
                        }
                    }
                }
                if(!flag){
                    dbHelper.addTask(title,desc,state,type,Random.nextInt(0,100))
                    dbHelper.addUser(title,Random.nextInt(0,50))
                    Toast.makeText(requireContext().applicationContext,"New task is added",
                    Toast.LENGTH_SHORT).show()
                    binding?.textTitle?.text?.clear()
                    binding?.taskDescription?.text?.clear()
                }
        }
    }
}
