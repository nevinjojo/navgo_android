package com.ccdhb.navgo.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ccdhb.navgo.R
import kotlinx.android.synthetic.main.player_layout.*
import kotlinx.android.synthetic.main.toolbar.*


class PlayerActivity: AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_layout)

        val bundle: Bundle? = intent.extras
        val titleText: String? = bundle!!.getString("Title")

        titleProgramm.text = titleText

        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}