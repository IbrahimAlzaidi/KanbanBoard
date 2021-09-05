package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentProfileBinding

class ProfileFragment:BaseFragment<FragmentProfileBinding>() {
    override val LOG_TAG: String = "Profile Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {

    }

}