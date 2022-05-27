package com.batuhan.todoapp.ui.listEdit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.todoapp.R
import com.batuhan.todoapp.db.DataBase
import com.batuhan.todoapp.db.SharedDB
import kotlinx.android.synthetic.main.recyclerlist_edit_item.view.*

class ListEditAdapter(
    val context: Context
) : RecyclerView.Adapter<ListEditAdapter.VHMainlist>() {
    class VHMainlist(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHMainlist {
        return VHMainlist(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerlist_edit_item, parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VHMainlist, position: Int) {
        holder.itemView.txtTitleList.text = SharedDB.showingList[position].title
        holder.itemView.txtTextList.text = SharedDB.showingList[position].text

        holder.itemView.deleteButtonListEditItem.setOnClickListener {
            DataBase.deleteTodoFromList(SharedDB.showingList[position].uid)
            SharedDB.showingList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return SharedDB.showingList.size
    }
}