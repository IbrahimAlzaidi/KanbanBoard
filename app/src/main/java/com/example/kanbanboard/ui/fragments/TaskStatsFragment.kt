package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentTaskBinding


class TaskStatsFragment:BaseFragment<FragmentTaskBinding>() {

    override val LOG_TAG: String = "TaskStats Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentTaskBinding
        get() = FragmentTaskBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {

    }

}