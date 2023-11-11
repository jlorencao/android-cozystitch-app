package com.example.cozystitchapp.model

data class CrochetPattern (
    val id: Int? = 0,
    val imageUrl: String? = "",
    val name: String? = "",
    val rows: List<String>? = listOf()
)