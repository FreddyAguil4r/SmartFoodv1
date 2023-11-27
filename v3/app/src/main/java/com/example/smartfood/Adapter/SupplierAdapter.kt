package com.example.smartfood.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfood.ModelResponse.SupplierResponse
import com.example.smartfood.R

class SupplierAdapter(private val suppliers: List<SupplierResponse>): RecyclerView.Adapter<SupplierAdapter.ViewHolder>() {

    /*
    val titles = arrayOf("Adelmi Global Food",
        "Agrovado - Agroindustrial",
        "Alfrimac Perú",
        "Alimentos de Exportación",
        "Apimás")
     */

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textTitle : TextView
        init {
            textTitle = itemView.findViewById(R.id.item_supplier)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_supplier,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SupplierAdapter.ViewHolder, position: Int) {
        holder.textTitle.text = suppliers[position].name
    }

    override fun getItemCount(): Int {
        return suppliers.size
        //return titles.size
    }
}