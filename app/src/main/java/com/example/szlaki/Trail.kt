package com.example.szlaki

class Trail(
    val id: Int,
    val name: String,
    val description: String,
    val points: Array<String>,
    val thumbnail: String,
    var times: MutableList<String> = mutableListOf(),
    var isRunning: Boolean = false,
    var seconds: Int = 0,
    var minutes: Int = 0,
    var hours: Int = 0

)