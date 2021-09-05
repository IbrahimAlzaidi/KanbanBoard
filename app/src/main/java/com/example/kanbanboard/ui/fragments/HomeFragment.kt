package com.example.kanbanboard.ui.fragments


import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentHomeBinding

class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = "Home Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override fun setup() {

    }

    override fun addCallBack() {
    }

}