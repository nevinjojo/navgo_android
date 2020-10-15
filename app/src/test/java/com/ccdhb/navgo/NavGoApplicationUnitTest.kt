package com.ccdhb.navgo

import com.ccdhb.navgo.Models.NavGoApplication
import org.junit.Test

import org.junit.Assert.*

class NavGoApplicationUnitTest {
    @Test
    fun checkFacilitiesCount() {
        assertEquals(NavGoApplication.facilities.size, 16);
    }

    @Test
    fun checkForMapwizeKey() {
        assertEquals(NavGoApplication.MAPWIZE_API_KEY, "dcb2be8d9ea4246365925170e680ddb6");
    }

    @Test
    fun checkForFirstFacility() {
        assertEquals(NavGoApplication.facilities[0].name, "Main Entrance, Level 2");
        assertEquals(NavGoApplication.facilities[0].color, R.color.colorGreen);
        assertEquals(NavGoApplication.facilities[0].placeId, "5d553553ea35b90050c05ce7");
        assertEquals(NavGoApplication.facilities[0].icon, R.drawable.ic_main_entrance);
    }
}