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
        val adapter = TaskAdapter(DbHelper(requireContext()).getAllTasksData())
        binding?.taskRecyclerView?.adapter = adapter
    }

    override fun addCallBack() {

    }

}