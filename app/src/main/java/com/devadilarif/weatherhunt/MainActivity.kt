package com.devadilarif.weatherhunt

import android.content.Intent
import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.devadilarif.weatherhunt.R.*
import com.devadilarif.weatherhunt.fragments.HomeFragment
import com.devadilarif.weatherhunt.fragments.NewsFragment
import com.devadilarif.weatherhunt.fragments.SettingFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout.addOnTabSelectedListener(this)
        showFragment(HomeFragment())
        Timber.plant(Timber.DebugTree())

    }



    override fun onTabReselected(p0: TabLayout.Tab?) {
        Timber.d("onTabReselected ${p0?.position}")

    }


    private fun showFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        Timber.d("onTabUnselected ${p0?.position}")
        when(p0?.position){
            0 -> {
                showFragment(HomeFragment())
                p0.icon =  getDrawable(R.drawable.ic_cloudicon_light)
            }
            1-> {
                showFragment(NewsFragment())
                p0.icon =  getDrawable(R.drawable.ic_newsicon)

            }
            2-> {
                showFragment(SettingFragment())
                p0.icon =  getDrawable(R.drawable.ic_settingicon)

            }
        }
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        Timber.d("onTabSelected ${p0?.position}")
        when(p0?.position){
            0 -> {
                showFragment(HomeFragment())
                p0.icon =  getDrawable(R.drawable.ic_cloudicon)
            }
            1-> {
                showFragment(NewsFragment())
                p0.icon =  getDrawable(R.drawable.ic_newsicon_dark)

            }
            2-> {
                showFragment(SettingFragment())
                p0.icon =  getDrawable(R.drawable.ic_settingicon_dark)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }







}
