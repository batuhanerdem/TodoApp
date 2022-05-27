package com.batuhan.todoapp.db

import android.content.Context
import com.batuhan.todoapp.model.Todo

object SharedDB {
    var isLineOn = false
    var showingList = arrayListOf<Todo>()

    fun setLineOn(context: Context) {
        val sharedPref = context.getSharedPreferences("TodoApp", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLineOn", isLineOn)
            apply()
        }
    }

    fun getLineOn(context: Context) {
        val sharedPref = context.getSharedPreferences("TodoApp", Context.MODE_PRIVATE)
        isLineOn = sharedPref.getBoolean("isLineOn", false)
    }
}