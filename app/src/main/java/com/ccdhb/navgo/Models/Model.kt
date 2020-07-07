package com.ccdhb.navgo.Models

class Model {
    var imageDownloadURL: String? = null
    var description: String? = null
    var caption: String? = null
    var audioURL: String? = null

    constructor():this("","", "", "") {

    }

    constructor(imageDownloadURL: String?, description: String?, caption: String?, audioURL: String?) {
        this.imageDownloadURL = imageDownloadURL
        this.description = description
        this.caption = caption
        this.audioURL = audioURL
    }
}