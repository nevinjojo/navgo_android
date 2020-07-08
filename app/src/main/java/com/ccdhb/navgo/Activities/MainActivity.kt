package com.ccdhb.navgo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ccdhb.navgo.Fragments.FacilitiesFragment
import com.ccdhb.navgo.Fragments.HomeFragment
import com.ccdhb.navgo.R
import com.ccdhb.navgo.Fragments.SoundsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when {
                menuItem.itemId == R.id.mainOption -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                menuItem.itemId == R.id.soundOption -> {
                    loadFragment(SoundsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                menuItem.itemId == R.id.breathOption -> {
                    loadFragment(FacilitiesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }
}
