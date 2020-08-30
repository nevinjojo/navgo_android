package com.ccdhb.navgo.Activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ccdhb.navgo.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        isColorTransitionsEnabled = true

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                title = "Indoor Navigation",
                description = "Easily navigate around the hospital using the built-in map feature.",
                imageDrawable = R.mipmap.indoor_navigation
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Appointments",
                description = "Keep track of all your appointments in one place.",
                imageDrawable = R.mipmap.appointments
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Facilities",
                description = "Find all the nearby facilities in the hospital.",
                imageDrawable = R.mipmap.facilities
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Accessibility",
                description = "Utilise the accessibility features for a better user experience.",
                imageDrawable = R.mipmap.accessibility
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        try {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        try {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
