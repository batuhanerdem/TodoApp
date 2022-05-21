package com.batuhan.todoapp.ui

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.text.set
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.Todo

class TodoEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_duzenle)
        val kaydetButton = findViewById<Button>(R.id.kaydetDuzenle)
        val iptalButton = findViewById<Button>(R.id.iptalEtDuzenle)
        val silButton = findViewById<Button>(R.id.silDuzenle)
        val editText = findViewById<EditText>(R.id.duzenlenecekTodoEditText)

        val id = intent.getIntExtra("id", 0)
        val text = intent.getStringExtra("text")
        editText.setText(text)


        kaydetButton.setOnClickListener() {
            DataBase.editDataBase(id, editText.text.toString())

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        iptalButton.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        silButton.setOnClickListener() {
            DataBase.deleteFromDataBase(id)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}