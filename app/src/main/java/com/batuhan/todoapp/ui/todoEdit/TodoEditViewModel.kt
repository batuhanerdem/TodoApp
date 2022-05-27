package com.batuhan.todoapp.ui.todoEdit

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.ui.todoAddToList.TodoAddToList

class TodoEditViewModel : ViewModel() {
    fun editDB(id: Int, title: String, text: String) {
        DataBase.editDataBase(id, title, text)
    }
    fun deleteFromDB(id:Int){
        DataBase.deleteFromDataBase(id)
    }
    fun throwIdToTodoAddToList(id:Int,context:Context){
        val intent = Intent(context, TodoAddToList::class.java)
        intent.putExtra("todoIdsi", id)
        context.startActivity(intent)
    }

}