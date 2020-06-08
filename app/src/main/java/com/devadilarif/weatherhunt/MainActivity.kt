package com.devadilarif.weatherhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devadilarif.weatherhunt.fragments.HomeFragment
import com.devadilarif.weatherhunt.fragments.NewsFragment
import com.devadilarif.weatherhunt.fragments.SettingFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout.addOnTabSelectedListener(this)
        showFragment(HomeFragment())

    }



    override fun onTabReselected(p0: TabLayout.Tab?) {

    }


    private fun showFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment)
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when(p0?.position){
            0 -> showFragment(HomeFragment())
            1-> showFragment(NewsFragment())
            2-> showFragment(SettingFragment())
        }
    }






}
