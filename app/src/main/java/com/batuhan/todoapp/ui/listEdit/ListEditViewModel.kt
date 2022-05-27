package com.batuhan.todoapp.ui.listEdit

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase

class ListEditViewModel : ViewModel() {
    fun addToShowingList(listId: Int) {
        DataBase.addToShowingList(listId)
    }
}