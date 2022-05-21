package com.batuhan.todoapp.ui

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.Todo

class MainViewModel : ViewModel(){

    var todoList = DataBase.getFromDB()

    fun updateTodoList(){
        todoList.clear()
        todoList.addAll(DataBase.getFromDB())
    }

}