package com.example.kanbanboard.model

data class DbTaskModel(
    var idTask      : Int,
    var titleTask   : String,
    var descTask    : String,
    var statsTask   : String,
    var typeTask    :String,
    var dateTask    :Int
)
