package com.batuhan.todoapp.ui.settings

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.SharedDB

class SettingsActivity : AppCompatActivity() {
    private lateinit var viewModel: SettingsViewModel
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        val switchLine = findViewById<Switch>(R.id.switchLine)

        switchLine.isChecked = SharedDB.isLineOn

        switchLine.setOnClickListener() {
            viewModel.changeIsLineOn(switchLine.isChecked,this)

        }
    }
}