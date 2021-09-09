package com.example.kanbanboard.model

data class DbTaskModel(
    var idTask      : Int,
    var titleTask   : String,
    var descTask    : String,
    var statsTask   : String,
    val typeTask    : String,
    val dateTask    : String?,
    var userName   : String,
)
