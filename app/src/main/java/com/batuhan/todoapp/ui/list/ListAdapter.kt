package com.batuhan.todoapp.ui.list

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
    private var listList: ArrayList<List>,
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

    override fun onBindViewHolder(holder: VHMainlist, position: Int) {
        val currentList = listList[position]
        holder.itemView.apply {
            if (adding) {//this is for the recyclerlist_item
                editListButton.visibility = INVISIBLE
                listTextView.setOnClickListener {
                    DataBase.setId(todoId, currentList.id)
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            }

            listTextView.text = currentList.name
            editListButton.setOnClickListener {
                val intent = Intent(context, ListEditActivity::class.java)
                intent.putExtra("listId", currentList.id)
                context.startActivity(intent)
            }
            deleteButtonListItem.setOnClickListener() {
                DataBase.apply {
                    deleteFromListDatabase(currentList.id)
                    updateList(listList)
                }
                notifyItemRemoved(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return listList.size
    }
}