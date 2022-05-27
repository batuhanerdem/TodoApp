package com.batuhan.todoapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.db.SharedDB

class MainViewModel : ViewModel(){

    var todoList = DataBase.getFromDB()

    fun updateTodoList(){
        todoList.clear()
        todoList.addAll(DataBase.getFromDB())
    }

    fun getIsLineOn(context: Context){

        SharedDB.getLineOn(context)

    }

}