package com.batuhan.todoapp.ui

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.db.SharedDB
import com.batuhan.todoapp.model.Todo
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MainAdapter(val todoList: ArrayList<Todo>, val context: Context) :
    RecyclerView.Adapter<MainAdapter.VHMainlist>() {
    class VHMainlist(itemView: View) : RecyclerView.ViewHolder(itemView)


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
        val title = todoList[position].title
        val text = todoList[position].text

        holder.itemView.txtText.maxLines = 2
        holder.itemView.txtTitle.text = todoList[position].title
        holder.itemView.txtTitle.doLineOn(todoList[position].isDone)
        holder.itemView.cbDone.isChecked = todoList[position].isDone
        holder.itemView.txtText.text = todoList[position].text

        holder.itemView.txtTitle.setOnClickListener() {
            goToFullScreen(context,title,text)
        }
        holder.itemView.txtText.setOnClickListener() {
            goToFullScreen(context,title,text)
        }

        holder.itemView.duzenButton.setOnClickListener {
            val myIntent = Intent(context, TodoEditActivity::class.java)
            myIntent.putExtra("id", id)
            myIntent.putExtra("title", title)
            myIntent.putExtra("text", text)
            context.startActivity(myIntent)
        }

        holder.itemView.cbDone.setOnCheckedChangeListener() { _, b ->
            holder.itemView.txtTitle.doLineOn(b)
            DataBase.checkedChange(todoList[position].uid, b)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    private fun TextView.doLineOn(boolean: Boolean) {
        if (SharedDB.isLineOn && boolean) {
            this.setPaintFlags(
                this.getPaintFlags()
                        or Paint.STRIKE_THRU_TEXT_FLAG
            )
        } else {
            this.setPaintFlags(
                this.getPaintFlags()
                        and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            )
        }
    }

    fun goToFullScreen(context: Context, title: String, text: String) {
        val myIntent = Intent(context, FullScreenActivity::class.java)
        myIntent.putExtra("title", title)
        myIntent.putExtra("text", text)
        context.startActivity(myIntent)
    }
}
