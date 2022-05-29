package com.batuhan.todoapp.ui.listEdit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R

class ListEditActivity : AppCompatActivity() {
    private lateinit var adapter: ListEditAdapter
    private lateinit var viewModel: ListEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_edit)

        viewModel = ViewModelProvider(this).get(ListEditViewModel::class.java)
        val listId = intent.getIntExtra("listId", -1)
        setRV(listId)
    }

    private fun setRV(listId: Int) {
        val recycler = findViewById<RecyclerView>(R.id.recyclerListEdit)

        viewModel.addToShowingList(listId)
        adapter = ListEditAdapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}
