package com.batuhan.todoapp.ui.listAdding

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase

class ListAddingViewModel: ViewModel() {
    fun addToListDB(listName: String){
        DataBase.addToListDatabase(listName)
    }
}