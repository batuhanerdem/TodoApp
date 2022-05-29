package com.batuhan.todoapp.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.ui.listAdding.ListAddingActivity

class ListActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        setRV()

        val addButton = findViewById<Button>(R.id.listAddingButton)

        addButton.setOnClickListener() {
            val intent = Intent(this, ListAddingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setRV() {
        val recycler = findViewById<RecyclerView>(R.id.recyclerList)

        adapter = ListAdapter(viewModel.listList, this, false)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}