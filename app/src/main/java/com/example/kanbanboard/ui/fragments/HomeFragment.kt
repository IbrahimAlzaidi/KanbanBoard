package com.example.kanbanboard.ui.fragments


import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.databinding.ItemTaskBinding
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    private lateinit var item_task :ItemTaskBinding
    private var array= listOf("1","2","3")

    private var spinnerAdapter: ArrayAdapter<String>? = null

    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override fun setup() {
       getSpinner()

    }
    private fun getSpinner(){
        spinnerAdapter!!.addAll(array)
        spinnerAdapter = ArrayAdapter(requireContext(),  R.layout.spinner_info ,array )
        spinnerAdapter!!.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        item_task.spinnerServices.adapter = spinnerAdapter
    }

    override fun addCallBack() {
    }

}