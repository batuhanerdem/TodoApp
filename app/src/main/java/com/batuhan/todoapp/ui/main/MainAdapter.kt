package com.batuhan.todoapp.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.db.SharedDB
import com.batuhan.todoapp.model.Todo
import com.batuhan.todoapp.ui.fullscreen.FullScreenActivity
import com.batuhan.todoapp.ui.todoEdit.TodoEditActivity
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlinx.android.synthetic.main.recyclerview_item.view.cbDoneList
import kotlinx.android.synthetic.main.recyclerview_item.view.txtTextList
import kotlinx.android.synthetic.main.recyclerview_item.view.txtTitleList

class MainAdapter(
    private val todoList: ArrayList<Todo>,
    val context: Context,
) :
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

            holder.itemView.txtTitleList.setOnClickListener {
                goToFullScreen(context, title, text)
            }
            holder.itemView.txtTextList.setOnClickListener {
                goToFullScreen(context, title, text)
            }
            holder.itemView.editButton.setOnClickListener {
                val myIntent = Intent(context, TodoEditActivity::class.java)

                myIntent.putExtra("id", id)
                myIntent.putExtra("title", title)
                myIntent.putExtra("text", text)
                context.startActivity(myIntent)
            }

            holder.itemView.cbDoneList.setOnCheckedChangeListener { _, b ->
                holder.itemView.txtTitleList.doLineOn(b)
                DataBase.checkedChange(todoList[position].uid, b)
            }

            holder.itemView.txtTextList.maxLines = 2
            holder.itemView.txtTitleList.text = todoList[position].title
            holder.itemView.txtTitleList.doLineOn(todoList[position].isDone)
            holder.itemView.cbDoneList.isChecked = todoList[position].isDone
            holder.itemView.txtTextList.text = todoList[position].text

    }

    override fun getItemCount(): Int {
            return todoList.size
    }

    private fun TextView.doLineOn(boolean: Boolean) {
        if (SharedDB.isLineOn && boolean) {
            this.paintFlags = (this.paintFlags
                    or Paint.STRIKE_THRU_TEXT_FLAG)
        } else this.paintFlags = (this.paintFlags
                and Paint.STRIKE_THRU_TEXT_FLAG.inv())
    }

    private fun goToFullScreen(context: Context, title: String, text: String) {
        val myIntent = Intent(context, FullScreenActivity::class.java)
        myIntent.putExtra("title", title)
        myIntent.putExtra("text", text)
        context.startActivity(myIntent)
    }
}
