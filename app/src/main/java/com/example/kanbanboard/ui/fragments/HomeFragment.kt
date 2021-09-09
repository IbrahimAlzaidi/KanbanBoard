package com.example.kanbanboard.ui.fragments


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.FragmentHomeBinding
import com.example.kanbanboard.databinding.ItemTaskBinding
import com.example.kanbanboard.ui.TaskAdapter


class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    private  var item_task :ItemTaskBinding? = null

    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    private val profileFragment = ProfileFragment()
    private val inputFragment = InputFragment()
    var dbHelper:DbHelper? = null

    override fun setup() {
       getSpinner()
       setupSearchView()
       setupRecycleView()
       displayProfileFragment()
       getChipsFiltered()


    }

    private fun getChipsFiltered() {
        binding?.textTodo?.setOnClickListener {
            dbHelper?.filterTaskByStats("To Do")
        }
        binding?.textInProgress?.setOnClickListener {
            dbHelper?.filterTaskByStats("in progress")
        }
        binding?.textDone?.setOnClickListener {
            dbHelper?.filterTaskByStats("Done")
        }
        binding?.textInBackLog?.setOnClickListener {
            dbHelper?.filterTaskByStats("in backlog")
        }
    }

    private fun displayProfileFragment() {
        item_task?.userText
            ?.setOnClickListener {
            val transaction = activity?.supportFragmentManager!!.beginTransaction()
                .add(R.id.container,profileFragment)
                .addToBackStack(null)
                .commit()
        }
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
