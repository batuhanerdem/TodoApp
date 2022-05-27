package com.batuhan.todoapp.ui.todoAdding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.batuhan.todoapp.R
import com.batuhan.todoapp.ui.main.MainActivity

class TodoAddingActivity : AppCompatActivity() {

    private lateinit var viewModel: TodoAddingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add)
        viewModel = ViewModelProvider(this).get(TodoAddingViewModel::class.java)

        val saveButton = findViewById<Button>(R.id.saveTodoAddButton)
        val cancelButton = findViewById<Button>(R.id.cancelTodoAddButton)
        val editTitle = findViewById<EditText>(R.id.todoAddEditTitle)
        val editText = findViewById<EditText>(R.id.todoAddEditText)

        saveButton.setOnClickListener {
            viewModel.addToDatabase(editTitle.text.toString(),
                                    editText.text.toString())
            goToMainActivity()
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

    }
    private fun goToMainActivity(){
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
}