package com.ccdhb.navgo.Activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ccdhb.navgo.Models.NavGoApplication
import com.ccdhb.navgo.R
import io.indoorlocation.basicbeaconlocationprovider.BasicBeaconIndoorLocationProvider
import io.indoorlocation.gps.GPSIndoorLocationProvider
import io.mapwize.mapwizesdk.api.Floor
import io.mapwize.mapwizesdk.api.MapwizeObject
import io.mapwize.mapwizesdk.api.Place
import io.mapwize.mapwizesdk.map.MapOptions
import io.mapwize.mapwizesdk.map.MapwizeMap
import io.mapwize.mapwizeui.MapwizeFragment
import io.mapwize.mapwizeui.MapwizeFragment.OnFragmentInteractionListener
import io.mapwize.mapwizeui.MapwizeFragmentUISettings
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class MapActivity : AppCompatActivity(), OnFragmentInteractionListener {

    private var mapwizeFragment: MapwizeFragment? = null
    private var basicBeaconIndoorLocationProvider: BasicBeaconIndoorLocationProvider? = null
    private var gpsIndoorLocationProvider: GPSIndoorLocationProvider? = null
    var mapwizeMap: MapwizeMap? = null
    private val MY_PERMISSION_ACCESS_FINE_LOCATION = 0
    private val FROM_DIRECTION_POINT_ID = ""
    private var TO_DIRECTION_POINT_ID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)

        // Fetch Destination Information
        val bundle: Bundle? = intent.extras
        val facilityName: String? = bundle!!.getString("Name")
        val facilityFloor: Int? = bundle.getInt("Floor")
        TO_DIRECTION_POINT_ID = bundle.getString("DestinationID").toString()

        // Back
        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Initialise the Mapwize UI
        val opts = MapOptions.Builder()
            .language(Locale.getDefault().language)
            .universe("5d37a075ea07e8001673a95d")
            .restrictContentToVenueId("5d37a168076ab60016fbff11")
            .centerOnPlace(TO_DIRECTION_POINT_ID)
            .build()
        val uiSettings = MapwizeFragmentUISettings.Builder()
            .menuButtonHidden(true)
            .build()
        mapwizeFragment = MapwizeFragment.newInstance(opts, uiSettings)

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.mapFragmentContainer, mapwizeFragment!!)
        ft.commit()

        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        mapwizeFragment!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapwizeFragment!!.onResume()
    }

    override fun onPause() {
        mapwizeFragment!!.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapwizeFragment!!.onStop()
        super.onStop()
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        super.onSaveInstanceState(saveInstanceState)
        mapwizeFragment!!.onSaveInstanceState(saveInstanceState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapwizeFragment!!.onLowMemory()
    }

    override fun onDestroy() {
        mapwizeFragment!!.onDestroy()
        super.onDestroy()
    }

    private fun startLocationService() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSION_ACCESS_FINE_LOCATION
            )
        } else {
            setupLocationProvider()
        }
    }

    private fun setupLocationProvider() {
        gpsIndoorLocationProvider = GPSIndoorLocationProvider(this)
        basicBeaconIndoorLocationProvider = BasicBeaconIndoorLocationProvider(
            this,
            NavGoApplication.MAPWIZE_API_KEY,
            gpsIndoorLocationProvider
        )
        basicBeaconIndoorLocationProvider!!.start()
        mapwizeMap!!.setIndoorLocationProvider(basicBeaconIndoorLocationProvider!!)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSION_ACCESS_FINE_LOCATION -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    setupLocationProvider()
                }
            }
        }
    }

    // The user clicked on the menu button (left button on the search bar).
    override fun onMenuButtonClick() {

    }

    // See Information Button section below.
    override fun onInformationButtonClick(mapwizeObject: MapwizeObject) {

    }

    // Contains both Mapbox map and Mapwize plugin. The fragment is ready to use.
    override fun onFragmentReady(mapwizeMap: MapwizeMap) {
        this.mapwizeMap = mapwizeMap
        startLocationService()
    }

    // The user clicked on the follow user button but no location has been found.
    override fun onFollowUserButtonClickWithoutLocation() {
        Log.i("Debug", "onFollowUserButtonClickWithoutLocation")
    }

    // Method called when a place or a place list is selected. Return true if you want to show the information button in the bottom view.
    override fun shouldDisplayInformationButton(mapwizeObject: MapwizeObject): Boolean {
        Log.i("Debug", "shouldDisplayInformationButton")

        when (mapwizeObject) {
            is Place -> return true
        }
        return false
    }

    // Method called when the available floors list changed. Return true if you want to display the floor controller.
    override fun shouldDisplayFloorController(floors: MutableList<Floor>): Boolean {
        Log.i("Debug", "shouldDisplayFloorController")
        if (floors == null || floors.size <= 1) {
            return false
        }
        return true
    }
}