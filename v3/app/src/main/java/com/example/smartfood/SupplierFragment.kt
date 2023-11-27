package com.example.smartfood

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfood.Adapter.SupplierAdapter
import com.example.smartfood.ModelResponse.SupplierResponse
import com.example.smartfood.Service.APIServiceSupplier
import com.example.smartfood.databinding.FragmentSupplierBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SupplierFragment : Fragment() {
    private lateinit var binding: FragmentSupplierBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SupplierAdapter

    private val supplierNames = mutableListOf<SupplierResponse>()

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentSupplierBinding.inflate(inflater)


       // Configura el RecyclerView y el adaptador
       recyclerView = binding.rcyViewSupplier
       recyclerView.layoutManager = LinearLayoutManager(requireContext())

       adapter = SupplierAdapter(supplierNames)
       recyclerView.adapter = adapter

       searchAllSupplier()

       //FloatingButton
       binding.floatingButton.setOnClickListener{nextFragment()}
       return binding.root
   }

    private fun nextFragment() {
        // Infla el layout del Custom Dialog
        val dialogView: View = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_layout_supplier, null)

        // Crea un AlertDialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Crea el AlertDialog
        val alertDialog = builder.create()

        // Muestra el AlertDialog
        alertDialog.show()
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://26.54.240.231:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchAllSupplier(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIServiceSupplier::class.java).getAllSuplier("supplier/all")
            val sup = call.body()
            //Variable donde esta la respuesta
            withContext(Dispatchers.Main){
                if(call.isSuccessful){
                    val names = sup?.name ?: ""
                    val supplier = SupplierResponse(id = 0, name = names, ruc = "", address = "")
                    supplierNames.clear()
                    supplierNames.add(supplier)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
    }

    /*
    private fun searchSupplierById(query:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIServiceSupplier::class.java).getSupplierById("supplier/$query")
            //Variable donde esta la respuesta
            val sup = call.body()
            if(call.isSuccessful){

            }else{

            }
        }
    }*/

}