package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.FragmentProfileBinding
import com.example.kanbanboard.ui.TaskAdapter
import com.example.kanbanboard.ui.TaskAdapterProfile

class ProfileFragment:BaseFragment<FragmentProfileBinding>() {
    override val LOG_TAG: String = "Profile Fragment"
    val daily = "4"
    val weekly = "18"
    override val bindingInflater: (LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun setup() {
        binding?.textDaily?.text = daily
        binding?.textWeekly?.text = weekly
        binding?.userTextImage?.text = binding?.textUserName?.text.toString().subSequence(0,2)
        val adapter = TaskAdapterProfile(DbHelper(requireContext()).getAllTasksByUser(binding?.textUserName?.text.toString()))
        binding?.taskRecyclerView?.adapter = adapter
    }

    override fun addCallBack() {

    }

}