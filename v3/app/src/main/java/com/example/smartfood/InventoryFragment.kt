package com.example.smartfood

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfood.Adapter.InventoryAdapter
import com.example.smartfood.databinding.FragmentInventoryBinding

class InventoryFragment : Fragment() {
    private lateinit var binding: FragmentInventoryBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInventoryBinding.inflate(inflater)


        // Configura el RecyclerView y el adaptador
        recyclerView = binding.inventoryrcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = InventoryAdapter()
        recyclerView.adapter = adapter

        //FloatActingButton
        binding.floatingButton.setOnClickListener{nextFragment()}
        return binding.root
    }

    private fun nextFragment() {
    // Infla el layout del Custom Dialog
        val dialogView: View = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_layout_inventory, null)

        // Crea un AlertDialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Crea el AlertDialog
        val alertDialog = builder.create()

        // Muestra el AlertDialog
        alertDialog.show()
    }
}