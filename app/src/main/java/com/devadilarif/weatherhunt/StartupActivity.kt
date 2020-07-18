package com.devadilarif.weatherhunt


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devadilarif.weatherhunt.fragments.SplashFragment


class StartupActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,SplashFragment()).commit()
//            Handler().postDelayed(this, 2000);
        }
    }





}