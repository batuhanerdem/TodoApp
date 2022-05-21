package com.batuhan.todoapp.ui

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
import com.batuhan.todoapp.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

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


        val ekleButton = findViewById<Button>(R.id.ekleButonu)
        ekleButton.setOnClickListener {
            val myIntent = Intent(this, TodoAddingActivity::class.java)
            startActivity(myIntent)
        }



    }
    private fun setRV(){
        val recycler = findViewById<RecyclerView>(R.id.recycler1)

        adapter = MainAdapter(viewModel.todoList,this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.ayarlar,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //kevserin istegi uzerine isaretlileri silme eklendi

        if (item.itemId == R.id.menuAyarlar){
            val myIntent = Intent(this,SettingsActivity::class.java)
            startActivity(myIntent)
        }
        else if (item.itemId == R.id.silme){
            DataBase.deleteCheckeds()
            viewModel.updateTodoList()
        }
        return super.onOptionsItemSelected(item)
    }
}