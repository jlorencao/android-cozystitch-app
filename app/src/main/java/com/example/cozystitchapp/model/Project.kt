package com.example.cozystitchapp.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date


data class Project(
    var title: String? = "",
    var type: String? = "",
    var crochetPattern: String? = "",
    var status: Int? = 0,
    @ServerTimestamp
    var createAt: Date? = null,
    var imagePath: String? = "",
    var yarn: String? = "",
    var hook: String? = "",
    var accessory: String = ""
): Serializable {
    constructor( title: String,type: String, crochetPattern: String?, status: Int, createAt: Date,imagePath: String,  yarn: String, hook: String, accessory: String ):this() {
        this.title = title
        this.type = type
        this.crochetPattern = crochetPattern
        this.status = status
        this.createAt = createAt
        this.imagePath = imagePath
        this.yarn = yarn
        this.hook = hook
        this.accessory = accessory
    }}