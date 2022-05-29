package com.batuhan.todoapp.ui.list

import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.DataBase

open class ListViewModel : ViewModel() {
    var listList = DataBase.getFromListDB()
}