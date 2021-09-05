package com.example.kanbanboard.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kanbanboard.databinding.ActivityHomeBinding
import com.example.kanbanboard.ui.fragments.HomeFragment
import com.example.kanbanboard.ui.fragments.ProfileFragment
import com.example.kanbanboard.ui.fragments.TaskStatsFragment

class HomeActivity : AppCompatActivity() {
    private val fragmentHome = HomeFragment()
    private val fragmentProfile = ProfileFragment()
    private val fragmentTaskStats = TaskStatsFragment()

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        addNavigationListener()
    }

//    private fun addNavigationListener() {
//        binding.navigationBar.setOnItemSelectedListener { item ->
//            replaceFragment(
//                when(item.itemId){
//                    R.id.nav_home -> fragmentHome
//                    R.id.nav_info -> fragmentTaskStats
//                    R.id.nav_search -> fragmentProfile
//                    R.id.nav_details -> fragmentDetails
//                    R.id.nav_vaccination_daily_info -> fragmentVaccination
//                    else -> return@setOnItemSelectedListener  false
//                }
//            )
//            return@setOnItemSelectedListener true
//        }
//    }

    private fun replaceFragment(newFragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, newFragment)
        transaction.commit()
    }

}