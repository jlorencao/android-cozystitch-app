package com.example.cozystitchapp.model

class User(
    var name: String,
    var lastname: String,
    var levelCrochet: String,
    var country: String,
    var projects: List<Project>?,
)