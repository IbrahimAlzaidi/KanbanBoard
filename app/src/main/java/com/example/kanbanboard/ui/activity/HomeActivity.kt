package com.example.kanbanboard.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kanbanboard.R
import com.example.kanbanboard.databinding.ActivityHomeBinding
import com.example.kanbanboard.ui.fragments.HomeFragment
import com.example.kanbanboard.ui.fragments.ProfileFragment
import com.example.kanbanboard.ui.fragments.TaskStatsFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {
    private val fragmentHome = HomeFragment()
    private val fragmentProfile = ProfileFragment()
    private val fragmentTaskStats = TaskStatsFragment()

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addNavigationListener()
    }

    private fun addNavigationListener() {
        binding.bottomNavigationView.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                replaceFragment(
                    when(newTab.id){
                        R.id.homePage -> fragmentHome
                        R.id.statisticPage -> fragmentTaskStats
                        R.id.profilePage -> fragmentProfile
                        else -> return
                    }
                )
            }
        })
    }


    private fun replaceFragment(newFragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, newFragment)
        transaction.commit()
    }
}
