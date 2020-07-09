package com.ccdhb.navgo.Models

import android.app.Application
import io.mapwize.mapwizesdk.core.MapwizeConfiguration

class NavGoApplication : Application() {

    // The Mapwize api key has to be provided to communicate with the backend and fetch your data
    private val MAPWIZE_API_KEY = "dcb2be8d9ea4246365925170e680ddb6"

    override fun onCreate() {
        super.onCreate()

        // Sets up the configuration for Mapwize with the API key
        val config = MapwizeConfiguration.Builder(this, MAPWIZE_API_KEY)
            .build()
        MapwizeConfiguration.start(config)
    }
}