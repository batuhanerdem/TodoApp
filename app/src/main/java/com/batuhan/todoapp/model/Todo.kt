package com.batuhan.todoapp.model

data class Todo(
    var title: String,
    var text: String,
    var isDone: Boolean = false,
    val uid: Int = 0
)
