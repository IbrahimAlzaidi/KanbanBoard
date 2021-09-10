package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import com.example.kanbanboard.databinding.FragmentAboutBinding

class AboutFragment:BaseFragment<FragmentAboutBinding>() {
    override val LOG_TAG: String
        get() = "About_Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentAboutBinding
        get() = FragmentAboutBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {

    }
}