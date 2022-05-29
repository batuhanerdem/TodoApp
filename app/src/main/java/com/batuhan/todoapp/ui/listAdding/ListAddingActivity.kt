package com.batuhan.todoapp.ui.listAdding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.batuhan.todoapp.R
import com.batuhan.todoapp.ui.list.ListActivity

class ListAddingActivity : AppCompatActivity() {
    private lateinit var viewModel: ListAddingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_adding)
        viewModel = ViewModelProvider(this).get(ListAddingViewModel::class.java)

        val saveButton = findViewById<Button>(R.id.saveListAdding)
        val cancelButton = findViewById<Button>(R.id.cancelListAdding)
        val editText = findViewById<EditText>(R.id.listAddingEditText)

        saveButton.setOnClickListener() {
            viewModel.addToListDB(editText.text.toString())
            goToListActivity()
        }

        cancelButton.setOnClickListener() {
            goToListActivity()
        }
    }

    private fun goToListActivity() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
        finish()
    }
}