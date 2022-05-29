package com.batuhan.todoapp.ui.todoAddToList

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase


class TodoAddToListViewModel : ViewModel() {
    var listList = DataBase.getFromListDB()
}