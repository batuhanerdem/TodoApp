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
        val currentTodo = todoList[position]
        val id = currentTodo.uid
        val title = currentTodo.title
        val text = currentTodo.text
        val isDone = currentTodo.isDone

        holder.itemView.apply {
            txtTitleList.setOnClickListener {
                goToFullScreen(context, title, text)
            }
            txtTextList.setOnClickListener {
                goToFullScreen(context, title, text)
            }
            editButton.setOnClickListener {
                Intent(context, TodoEditActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("title", title)
                    putExtra("text", text)
                    context.startActivity(this)
                }
            }

            cbDoneList.setOnCheckedChangeListener { _, b ->
                txtTitleList.doLineOn(b)
                DataBase.checkedChange(id, b)
            }

            txtTextList.maxLines = 2
            txtTitleList.text = title
            txtTitleList.doLineOn(isDone)
            cbDoneList.isChecked = isDone
            txtTextList.text = text
        }
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
        Intent(context, FullScreenActivity::class.java).apply {
            putExtra("title", title)
            putExtra("text", text)
            context.startActivity(this)
        }
    }
}
