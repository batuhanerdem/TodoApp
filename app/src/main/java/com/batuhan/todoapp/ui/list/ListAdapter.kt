package com.batuhan.todoapp.ui.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.model.List
import com.batuhan.todoapp.ui.listEdit.ListEditActivity
import com.batuhan.todoapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.recyclerlist_item.view.*

class ListAdapter(
    private val listList: ArrayList<List>,
    val context: Context,
    private val adding: Boolean = false,
    private val todoId: Int = 0
) :
    RecyclerView.Adapter<ListAdapter.VHMainlist>() {
    class VHMainlist(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHMainlist {
        return VHMainlist(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerlist_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VHMainlist, position: Int) {

        if (adding) {//this is for the recyclerlist_item
            holder.itemView.editListButton.visibility = INVISIBLE
            holder.itemView.listTextView.setOnClickListener {
                DataBase.setId(todoId, listList[position].id)
                val intent = Intent(context,MainActivity::class.java)
                context.startActivity(intent)
            }
        }

        holder.itemView.listTextView.text = listList[position].name
        holder.itemView.editListButton.setOnClickListener {
            val intent = Intent(context, ListEditActivity::class.java)
            intent.putExtra("listId", listList[position].id)
            context.startActivity(intent)
        }
        holder.itemView.deleteButtonListItem.setOnClickListener {
            DataBase.deleteFromListDatabase(listList[position].id)
            notifyDataSetChanged()
        }
        
    }

    override fun getItemCount(): Int {
        return listList.size
    }
}