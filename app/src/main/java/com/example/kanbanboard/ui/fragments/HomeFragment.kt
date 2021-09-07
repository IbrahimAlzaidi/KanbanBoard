package com.example.kanbanboard.ui.fragments


import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DataManger
import com.example.kanbanboard.databinding.ItemTaskBinding
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    private  var item_task :ItemTaskBinding? = null


    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override fun setup() {
       getSpinner()
       setupSearchView()
        setupRecycleView()

    }

    private fun setupRecycleView() {
       /* val adapter = binding!!.recyclerView(TODO("GET LIST FROM DATA MANAGER"))
        binding!!.recyclerView.adapter = adapter*/
    }

    @SuppressLint("ResourceAsColor")
    private fun setupSearchView() {
        binding!!.apply {
            searchBar.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                //click search icon in keyboard
                override fun onQueryTextSubmit(query: String) = TODO("IMPLEMENT LATER")
                override fun onQueryTextChange(newText: String?) = TODO("IMPLEMENT LATER")
            })
            searchBar.queryHint = "Search..."
        }
    }

    private fun getSpinner(){
         val array= listOf("1","2","3")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_info ,array )
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        item_task?.spinnerTaskType?.adapter = spinnerAdapter
    }

    override fun addCallBack() {
    }

}