package com.example.kanbanboard.ui.fragments


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.data.DbSchema
import com.example.kanbanboard.databinding.ItemTaskBinding
import com.example.kanbanboard.model.DbTaskModel
import com.example.kanbanboard.ui.TaskAdapter


class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    private  var item_task :ItemTaskBinding? = null

    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    val inputFragment = InputFragment()

    override fun setup() {
       getSpinner()
       setupSearchView()
        setupRecycleView()

    }

    private fun setupRecycleView() {
        val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksData())
        binding!!.recyclerView.adapter = adapter
    }

    @SuppressLint("ResourceAsColor")
    private fun setupSearchView() {
        binding!!.apply {
//            searchBar.setOnQueryTextListener(object :
//                androidx.appcompat.widget.SearchView.OnQueryTextListener {
//                //click search icon in keyboard
//                override fun onQueryTextSubmit(query: String) = TODO("IMPLEMENT LATER")
//                override fun onQueryTextChange(newText: String?) = TODO("IMPLEMENT LATER")
//            })
            searchBar.queryHint = "Search..."
        }
    }

    private fun getSpinner(){
         val array= listOf("To Do ","In progress","Done")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_info ,array )
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        item_task?.spinnerTaskType?.adapter = spinnerAdapter
    }

    override fun addCallBack() {
        binding?.appCompatButton?.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.add(R.id.container,inputFragment)
            transaction?.commit()
        }
      }
    }
