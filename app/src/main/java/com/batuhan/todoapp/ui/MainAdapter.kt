package com.batuhan.todoapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.Todo
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MainAdapter(val todoList: ArrayList<Todo>, val context: Context)
    : RecyclerView.Adapter<MainAdapter.VHMainlist>() {
    class VHMainlist(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHMainlist {
        return VHMainlist(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VHMainlist, position: Int) {
        val id = todoList[position].uid
        val text = todoList[position].title
        holder.itemView.txtTitle.text = todoList[position].title
        holder.itemView.cbDone.isChecked = todoList[position].isDone
        holder.itemView.duzenButton.setOnClickListener{
            val myIntent = Intent(context, TodoEditActivity::class.java)
            myIntent.putExtra("id", id)
            myIntent.putExtra("text",text)
            context.startActivity(myIntent)
        }
        holder.itemView.cbDone.setOnCheckedChangeListener { _, b ->
            DataBase.checkedChange(todoList[position].uid,b)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}
