package com.ccdhb.navgo.Models

import android.app.Application
import com.ccdhb.navgo.R
import com.mapbox.mapboxsdk.Mapbox
import io.mapwize.mapwizesdk.core.MapwizeConfiguration

class NavGoApplication : Application() {

    companion object {
        var facilities = createFacilities()

        // The Mapwize api key has to be provided to communicate with the backend and fetch your data
        var MAPWIZE_API_KEY = "dcb2be8d9ea4246365925170e680ddb6"

        /**
         * Returns an ArrayList of Facility objects.
         */
        fun createFacilities(): ArrayList<Facility> {
            val newFacilities = ArrayList<Facility>()
            newFacilities.add(Facility("Main Entrance, Level 2", 2, R.color.colorGreen, R.drawable.ic_main_entrance, "5d553553ea35b90050c05ce7"))
            newFacilities.add(Facility("Eye Clinic, Level 9", 9, R.color.colorBlue, R.drawable.ic_eye_clinic, "5d54c846e7a9e8001697a213"))
            newFacilities.add(Facility("Underground Parking, Level 1", 1, R.color.colorRed, R.drawable.ic_parking, "5f1cbab156c8db001622ef60"))
            newFacilities.add(Facility("Link Bridge, Level 3", 3, R.color.colorGreen, R.drawable.ic_link_bridge, "5d54cf1fa387b40016514eb7"))
            newFacilities.add(Facility("Cafe, Level 2", 2, R.color.colorBlue, R.drawable.ic_cafe, "5d54ce0b64e9c30016cbf3d8"))
            newFacilities.add(Facility("Intensive Care, Level 2", 2, R.color.colorRed, R.drawable.ic_intensive_care_unit, "5d78e53aaa0a83002c1a4deb"))
            newFacilities.add(Facility("Gift Shop, Level 2", 2, R.color.colorGreen, R.drawable.ic_gift_shop, "5d78e5daaa0a83002c1a4df3"))
            newFacilities.add(Facility("Restroom, Level 2", 2, R.color.colorBlue, R.drawable.ic_toilet, "5f17be64febab00016c832e8"))
            newFacilities.add(Facility("Restroom 2, Level 2", 2, R.color.colorBlue, R.drawable.ic_toilet, "5f17bf14febab00016c832ef"))
            newFacilities.add(Facility("Lift Green, Level 1", 1, R.color.colorGreen, R.drawable.ic_lift_green, "5f17bbd1febab00016c832d8"))
            newFacilities.add(Facility("Lift Orange, Level 1", 1, R.color.colorGreen, R.drawable.ic_lift_orange, "5f17bc251f03e100164131c1"))
            newFacilities.add(Facility("Lift Red, Level 1", 1, R.color.colorGreen, R.drawable.ic_lift_red, "5f17bc4bfeafe700167768a9"))
            newFacilities.add(Facility("Breastfeeding Room, Level 2", 2, R.color.colorBlue, R.drawable.ic_breastfeeding_room, "5f17c213febab00016c83310"))
            newFacilities.add(Facility("Disabled Restroom, Level 3", 3, R.color.colorBlue, R.drawable.ic_toilet, "5f17c34dfebab00016c83320"))
            newFacilities.add(Facility("Cafe, Level 5", 5, R.color.colorBlue, R.drawable.ic_cafe, "5f17c538feafe70016776901"))
            newFacilities.add(Facility("Restroom, Level 9", 9, R.color.colorBlue, R.drawable.ic_toilet, "5f17c67e22c0300016d10a1b"))

            return newFacilities
        }
    }

    override fun onCreate() {
        super.onCreate()

        Mapbox.getInstance(this, "pk.mapwize")
        // Sets up the global configuration for Mapwize with the API key
        val config = MapwizeConfiguration.Builder(this, MAPWIZE_API_KEY)
            .build()
        MapwizeConfiguration.start(config)
    }
}