package com.example.cozystitchapp.model

import java.io.Serializable


class CrochetPattern (
    var id: Int? = 0,
    var imageUrl: String? = "",
    var name: String? = "",
    var rows: List<String>? = null,
): Serializable {
    constructor(id: Int, imageUrl: String, name: String, rows: List<String>):this() {
        this.id = id
        this.imageUrl = imageUrl
        this.name = name
        this.rows = rows
    }
}