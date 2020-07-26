package com.ccdhb.navgo.Models

import com.ccdhb.navgo.R

class Appointment {
    var name: String? = null
    var facility: Facility? = null
    var dateTime: String? = null
    var icon: Int? = null

    constructor() : this("",null, "", R.drawable.ic_eye_clinic) {

    }

    constructor(name: String?, facility: Facility?, dateTime: String?, icon: Int?) {
        this.name = name
        this.facility = facility
        this.dateTime = dateTime
        this.icon = icon
    }
}