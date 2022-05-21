package com.batuhan.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.batuhan.todoapp.R

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        val textViewText = findViewById<TextView>(R.id.textViewText)
        var title = intent.getStringExtra("title")
        var text = intent.getStringExtra("text")

        textViewTitle.setText(title)
        textViewText.setText(text)
    }
}