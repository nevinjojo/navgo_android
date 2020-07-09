package com.ccdhb.navgo.Models

import android.graphics.Color
import com.ccdhb.navgo.R

class Facility {
    var name: String? = null
    var floor: Int? = null
    var color: Int? = null
    var icon: Int? = null
    var placeId: String? = null

    constructor():this("",0, R.color.colorPrimary, R.drawable.ic_cafe, "") {

    }

    constructor(name: String?, floor: Int?, color: Int?, icon: Int?, placeId: String?) {
        this.name = name
        this.floor = floor
        this.color = color
        this.icon = icon
        this.placeId = placeId
    }
}