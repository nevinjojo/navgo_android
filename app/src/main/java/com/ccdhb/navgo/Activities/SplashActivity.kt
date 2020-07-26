package com.ccdhb.navgo.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccdhb.navgo.R


class SplashActivity: AppCompatActivity() {

    private var mPreferences: SharedPreferences? = null
    private var sharedPrefFile = "com.ccdhb.navgo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        val background = object : Thread() {
            override fun run(){
                try {
                    sleep(1500)
                    var intent = Intent(baseContext, MainActivity::class.java)
                    if (mPreferences!!.getBoolean("firstrun", true)) {
                        intent = Intent(baseContext, OnboardingActivity::class.java)
                        mPreferences!!.edit().putBoolean("firstrun", false).apply();
                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
