package com.batuhan.todoapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.ui.settings.SettingsActivity
import com.batuhan.todoapp.ui.todoAdding.TodoAddingActivity
import com.batuhan.todoapp.ui.list.ListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBase.setupSQL(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getIsLineOn(this)
        setRV()

        val addButton = findViewById<Button>(R.id.addButton)
        val listButton = findViewById<Button>(R.id.listButton)

        addButton.setOnClickListener {
            val myIntent = Intent(this, TodoAddingActivity::class.java)
            startActivity(myIntent)
        }

        listButton.setOnClickListener{
            val myIntent = Intent(this, ListActivity::class.java)
            startActivity(myIntent)
        }

    }
    private fun setRV(){
        val recycler = findViewById<RecyclerView>(R.id.recyclerMain)

        adapter = MainAdapter(viewModel.todoList,this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.settings,menu)
        return super.onCreateOptionsMenu(menu)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //kevserin istegi uzerine isaretlileri silme eklendi

        if (item.itemId == R.id.menuAyarlar){
            val myIntent = Intent(this, SettingsActivity::class.java)
            startActivity(myIntent)
        }
        else if (item.itemId == R.id.silme){
            DataBase.deleteCheckeds()
            viewModel.updateTodoList()
            adapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
    }
}