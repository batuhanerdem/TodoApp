package com.batuhan.todoapp.ui.todoEdit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.batuhan.todoapp.R
import com.batuhan.todoapp.ui.main.MainActivity

class TodoEditActivity : AppCompatActivity() {
    private lateinit var viewModel: TodoEditViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_edit)
        viewModel = ViewModelProvider(this).get(TodoEditViewModel::class.java)

        val saveButton = findViewById<Button>(R.id.saveEditTodo)
        val cancelButton = findViewById<Button>(R.id.cancelEditTodo)
        val silButton = findViewById<Button>(R.id.deleteEditTodo)
        val editText = findViewById<EditText>(R.id.editTodoEditTextText)
        val editTitle = findViewById<EditText>(R.id.editTodoEditTextTitle)

        val id = intent.getIntExtra("id", -1)
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")

        editText.setText(text)
        editTitle.setText(title)

        saveButton.setOnClickListener() {
            viewModel.editDB(id, editTitle.text.toString(), editText.text.toString())
            goToMainActivity()
        }
        cancelButton.setOnClickListener() {
            goToMainActivity()
        }
        silButton.setOnClickListener() {
            viewModel.deleteFromDB(id)
            goToMainActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_to_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addToListMenu) {
            val id = intent.getIntExtra("id", -1)
            viewModel.throwIdToTodoAddToList(id, this)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}