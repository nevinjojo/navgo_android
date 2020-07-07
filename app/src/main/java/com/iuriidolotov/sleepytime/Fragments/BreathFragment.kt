//
// THIS APPLICATION WAS DEVELOPED BY IURII DOLOTOV
//
// IF YOU HAVE ANY QUESTIONS PLEASE DO NOT TO HESITATE TO CONTACT ME VIA MARKETPLACE OR EMAIL: utilityman.development@gmail.com
//
// THE AUTHOR REMAINS ALL RIGHTS TO THE PROJECT
//
// THE ILLEGAL DISTRIBUTION IS PROHIBITED
//

package com.iuriidolotov.sleepytime.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.viewanimator.ViewAnimator
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.iuriidolotov.sleepytime.R
import kotlinx.android.synthetic.main.breath_layout.*


class BreathFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.breath_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startButton.setOnClickListener {
            startAnimation()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun startAnimation() {
        ViewAnimator
            .animate(lotusImageView)
            .alpha(0f, 1f)
            .onStart {}
            .decelerate()
            .duration(2000)
            .thenAnimate(lotusImageView)
            .scale(0.02f, 1.5f, 0.02f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                lotusImageView.scaleX = 1.0f
                lotusImageView.scaleY = 1.0f
            }
            .start()
    }
}
