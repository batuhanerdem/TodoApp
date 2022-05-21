package com.batuhan.todoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.Todo

class TodoAddingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_ekle)

        val kaydetButton = findViewById<Button>(R.id.kaydetYeni)
        val iptalButton = findViewById<Button>(R.id.iptalEtYeni)
        val editTitle = findViewById<EditText>(R.id.todoEkleEditTitle)
        val editText = findViewById<EditText>(R.id.todoEkleEditText)

        kaydetButton.setOnClickListener {

            DataBase.addToDataBase(Todo(editTitle.text.toString(),editText.text.toString()))

            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        iptalButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

    }

}