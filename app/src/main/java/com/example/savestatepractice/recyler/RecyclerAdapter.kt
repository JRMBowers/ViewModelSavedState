package com.example.savestatepractice.recyler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savestatepractice.R

class RecyclerAdapter(listOfRecyclerItems : List<RecyclerItem>) : RecyclerView.Adapter<RecyclerAdapter.itemViewHolder>(){
    var recyclerItems = listOfRecyclerItems
    inner class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_items,parent,false)
        return itemViewHolder(view)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemView.apply {
           var text : TextView = findViewById<TextView>(R.id.tvTodoText)
            text.text = recyclerItems[position].text
        }
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }
}