package com.batuhan.todoapp.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.batuhan.todoapp.db.SharedDB

class SettingsViewModel : ViewModel() {

    fun changeIsLineOn(boolean: Boolean, context: Context) {
        SharedDB.isLineOn = boolean
        SharedDB.setLineOn(context)
    }
}

