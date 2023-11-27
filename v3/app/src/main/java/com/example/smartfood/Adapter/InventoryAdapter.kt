package com.example.smartfood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfood.R

class InventoryAdapter:RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    val titles = arrayOf("Zanahoria","Tomate","Carne Roja","res","conservas","suvenir","duraznos")

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textTitle : TextView
        var editButton : ImageButton
        var deleteButton : ImageButton
        init{
            textTitle = itemView.findViewById(R.id.item_product)
            editButton = itemView.findViewById(R.id.edit_button)
            deleteButton = itemView.findViewById(R.id.delete_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_inventory,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = titles[position]
    }


}