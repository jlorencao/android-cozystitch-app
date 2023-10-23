package com.example.cozystitchapp.model

class Pattern (
    val patternId: String,
    val patternName: String,
    val type: String,
    val rows: Int,
    val notes: String,
    val pdf: String?,
    val interactive: String,
    val instructions: List<String>?,
    val image: String,
)