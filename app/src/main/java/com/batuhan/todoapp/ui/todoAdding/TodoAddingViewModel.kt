package com.batuhan.todoapp.ui.todoAdding

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.Todo

class TodoAddingViewModel : ViewModel() {
    fun addToDatabase(title: String, text: String) {
        val newTodo = Todo(title, text)
        DataBase.addToDataBase(newTodo)
    }

}