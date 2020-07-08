package com.ccdhb.navgo.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccdhb.navgo.R
import kotlinx.android.synthetic.main.toolbar.*


class MapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)

        val bundle: Bundle? = intent.extras
        val facilityName: String? = bundle!!.getString("Name")
        val facilityFloor: String? = bundle.getString("Floor")
        println("facility name: $facilityName, facility floor:  $facilityFloor")
        if (facilityFloor == null) {
            // We might not need floor information. Only implement it if Mapwize requests for it.
            // TODO: Do something if floor information is not available
        }

        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}