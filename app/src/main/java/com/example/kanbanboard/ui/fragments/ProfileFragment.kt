package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.FragmentProfileBinding
import com.example.kanbanboard.ui.TaskAdapter

class ProfileFragment:BaseFragment<FragmentProfileBinding>() {
    override val LOG_TAG: String = "Profile Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun setup() {
        binding?.userText?.text.toString()
        val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksByUser("naufal"))
        binding?.taskRecyclerView?.adapter = adapter
    }

    override fun addCallBack() {

    }

}