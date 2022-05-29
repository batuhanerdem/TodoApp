package com.batuhan.todoapp.utils
//this checks if item is null, if it's not then it runs the function
fun safeLet(vararg items: Any?, callback: () -> Unit) {
    items.forEach { item ->
        item ?: run {
            return
        }
    }
    callback()
}