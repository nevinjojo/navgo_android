package com.ccdhb.navgo.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccdhb.navgo.R
import io.mapwize.mapwizesdk.api.Floor
import io.mapwize.mapwizesdk.api.MapwizeObject
import io.mapwize.mapwizesdk.map.MapOptions
import io.mapwize.mapwizesdk.map.MapwizeMap
import io.mapwize.mapwizeui.MapwizeFragment
import io.mapwize.mapwizeui.MapwizeFragment.OnFragmentInteractionListener
import io.mapwize.mapwizeui.MapwizeFragmentUISettings
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class MapActivity: AppCompatActivity(), OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)

        val opts = MapOptions.Builder()
            .language(Locale.getDefault().language)
            .universe("5d37a075ea07e8001673a95d")
            .centerOnVenue("5d37a168076ab60016fbff11")
            .build()
        val uiSettings = MapwizeFragmentUISettings.Builder()
            .menuButtonHidden(true)
            .build()
        val frag = MapwizeFragment.newInstance(opts, uiSettings)

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.mapFragmentContainer, frag)
        ft.commit()

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

    // The user clicked on the menu button (left button on the search bar).
    override fun onMenuButtonClick() {

    }

    // See Information Button section below.
    override fun onInformationButtonClick(mapwizeObject: MapwizeObject) {

    }

    // Contains both Mapbox map and Mapwize plugin. The fragment is ready to use.
    override fun onFragmentReady(mapwizeMap: MapwizeMap) {

    }

    // The user clicked on the follow user button but no location has been found.
    override fun onFollowUserButtonClickWithoutLocation() {

    }

    // Method called when a place or a place list is selected. Return true if you want to show the information button in the bottom view.
    override fun shouldDisplayInformationButton(mapwizeObject: MapwizeObject): Boolean {
        return true
    }

    // Method called when the available floors list changed. Return true if you want to display the floor controller.
    override fun shouldDisplayFloorController(floors: MutableList<Floor>): Boolean {
        return true
    }
}