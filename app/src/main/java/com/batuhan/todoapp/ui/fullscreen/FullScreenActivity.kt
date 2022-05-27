package com.batuhan.todoapp.ui.fullscreen

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
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")

        textViewTitle.text = title
        textViewText.text = text
    }
}