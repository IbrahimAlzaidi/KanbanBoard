package com.example.kanbanboard.ui.fragments


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.data.DbSchema
import com.example.kanbanboard.databinding.ItemTaskBinding
import com.example.kanbanboard.model.DbTaskModel
import com.example.kanbanboard.ui.TaskAdapter
import com.example.kanbanboard.util.ClickListener


class HomeFragment:BaseFragment<FragmentHomeBinding>(),ClickListener {
    private  var item_task :ItemTaskBinding? = null

    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    val profileFragment = ProfileFragment()
    val inputFragment = InputFragment()
     var dbHelper:DbHelper? = null
    val adapter= binding?.recyclerView?.adapter

    override fun setup() {
       getSpinner()
       setupSearchView()
        setupRecycleView()
        //displayProfileFragment()
        getChipsFiltered()


    }

    override fun onStart() {
        super.onStart()
        setupRecycleView()
    }

    private fun getChipsFiltered() {
        binding?.textInProgress?.setOnClickListener {
            val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksDataSpinner("in progress"),this)
            binding!!.recyclerView.adapter = adapter
        }
        binding?.textDone?.setOnClickListener {
            val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksDataSpinner("Done"),this)
            binding!!.recyclerView.adapter = adapter
        }
        binding?.textInBackLog?.setOnClickListener {
            setupRecycleView()
        }
    }

  /*  private fun displayProfileFragment() {
        item_task?.userText
            ?.setOnClickListener {
            val transaction = activity?.supportFragmentManager!!.beginTransaction()
                .add(R.id.container,profileFragment)
                .addToBackStack(null)
                .commit()
        }

    }*/

    private fun setupRecycleView() {
        val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksData(),this)
        binding!!.recyclerView.adapter = adapter
    }

    @SuppressLint("ResourceAsColor")
    private fun setupSearchView() {
        binding!!.apply {
           searchBar.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                //click search icon in keyboard
                override fun onQueryTextSubmit(query: String) = false
               override fun onQueryTextChange(newText: String?) = getSearch(newText!!)

           })
            searchBar.queryHint = "Search..."
        }
    }
    private fun getSearch(newText:String): Boolean {
        binding?.recyclerView.apply {
            val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksTitle(newText),this@HomeFragment)
            binding!!.recyclerView.adapter = adapter
            Log.v("title","${ dbHelper?.getAllTasksTitle(newText)}\n" )
        }
        return false

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
                ?.addToBackStack(null)
            transaction?.commit()
        }
      }

    override fun onUserPhotoClick(position: Int) {
        position
        when(position){
            0-> {val transaction = activity?.supportFragmentManager!!.beginTransaction()
                .add(R.id.container,profileFragment)
                .addToBackStack(null)
                .commit()
                adapter?.notifyItemChanged(position)
            }
        }

    }
}
