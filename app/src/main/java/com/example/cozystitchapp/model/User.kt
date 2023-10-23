package com.example.cozystitchapp.model

class User(
    var name: String,
    var lastname: String,
    var email: String,
    var levelCrochet: String,
    var projects: List<Project>,
)