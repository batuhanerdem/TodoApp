package com.batuhan.todoapp.ui.todoAddToList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.ui.list.ListAdapter

class TodoAddToList : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: TodoAddToListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add_to_list)
        viewModel = ViewModelProvider(this).get(TodoAddToListViewModel::class.java)
        val getTodoId = intent.getIntExtra("todoIdsi",-1)
        setRV(getTodoId)

    }

    private fun setRV(getTodoId:Int) {

        val recycler = findViewById<RecyclerView>(R.id.todoAddToListRecycler)
        adapter = ListAdapter(viewModel.listList, this, true, getTodoId)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}