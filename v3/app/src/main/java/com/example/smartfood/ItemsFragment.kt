package com.example.smartfood

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfood.Adapter.CategoryAdapter
import com.example.smartfood.Model.Category
import com.example.smartfood.Model.Product
import com.example.smartfood.databinding.DialogLayoutItemsBinding
import com.example.smartfood.databinding.FragmentItemsBinding
import com.example.smartfood.db.DatabaseHelper
import java.sql.ResultSet
import java.sql.Statement

class ItemsFragment : Fragment() {
    private lateinit var binding: FragmentItemsBinding

    private var categoryList = ArrayList<Category>()
    //private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemsBinding.inflate(inflater)

        //val dbSmartFood = getDatabase()

        //testConnection() PROBANDO LA CONEXION ????

        //RecyclerView
        binding.rcyView.layoutManager = LinearLayoutManager(requireContext())
        addDataTolist()
        val adapter = CategoryAdapter(categoryList)
        binding.rcyView.adapter = adapter

        //FloatActionButton
        binding.floatingButton.setOnClickListener{openDialog()}

        return binding.root
    }

    private fun testConnection() {
        // Crear una instancia de DatabaseHelper
        val db = DatabaseHelper ()

        // Obtener la conexión con la base de datos
        val connection = db.getConnection ()

        if (connection != null) {
            Toast.makeText (requireContext(), "Conexión exitosa", Toast.LENGTH_LONG).show ()
            // Crear un objeto Statement para ejecutar consultas SQL
            val statement: Statement = connection.createStatement ()
            // Ejecutar una consulta SQL para obtener todos los usuarios
            val query = "SELECT * FROM categories"
            val resultSet: ResultSet = statement.executeQuery (query)
            // Recorrer el resultado y mostrar los datos en un TextView
            while (resultSet.next ()) {
                val nombre = resultSet.getString ("name")
                binding.textView.append (nombre)
            }
            // Cerrar el resultado y la conexión
            resultSet.close ()
            connection.close ()
        } else {
            Toast.makeText (requireContext(), "Conexión fallida", Toast.LENGTH_LONG).show ()
        }
    }

    /*
    private fun getDatabase() {
        val url = "jdbc:google:mysql://bdsmartfood:southamerica-west1:smarfoodb/BDSmartFoodV4.0"
        val user = "omar"
        val password = "omar"
        val connectToCloudSQL = ConnectToCloudSQL(url,user,password)
        val thread = Thread(connectToCloudSQL)
        thread.start()
    }*/

    private fun addDataTolist() {
        val productItem1 = ArrayList<Product>()
        productItem1.add(Product(1,"zanahoria","11/25/2023","11/25/2023",1.0,1.0,1.0))
        productItem1.add(Product(2,"tomate","11/25/2023","11/25/2023",1.0,1.0,1.0))
        categoryList.add(Category(1,"Verduras",1.0,productItem1))

        val productItem2 = ArrayList<Product>()
        productItem2.add(Product(3,"carne roja","11/25/2023","11/25/2023",1.0,1.0,1.0))
        productItem2.add(Product(4,"res","11/25/2023","11/25/2023",1.0,1.0,1.0))
        categoryList.add(Category(2,"Carne",1.0,productItem2))

        val productItem3 = ArrayList<Product>()
        productItem3.add(Product(5,"enlatado de atun","11/25/2023","11/25/2023",1.0,1.0,1.0))
        productItem3.add(Product(6,"conservas","11/25/2023","11/25/2023",1.0,1.0,1.0))
        productItem3.add(Product(7,"suvenir","11/25/2023","11/25/2023",1.0,1.0,1.0))
        productItem3.add(Product(8,"duraznos","11/25/2023","11/25/2023",1.0,1.0,1.0))
        categoryList.add(Category(3,"Enlatados",1.0,productItem3))
    }

    private fun openDialog() {
        // Infla el layout del Custom Dialog
        val dialogView: View = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_layout_items, null)

        // Encuentra los Spinners en el diseño del dialogo
        val spinner1: Spinner = dialogView.findViewById(R.id.spinner_category)
        val spinner2: Spinner = dialogView.findViewById(R.id.spinnner_supplier)
        val cancelButton : Button = dialogView.findViewById(R.id.btnCancel)
        val saveButton : Button = dialogView.findViewById(R.id.btnSave)

        // Agrega datos de prueba a los Spinners
        val testData1 = arrayOf("Opción 1", "Opción 2", "Opción 3")
        val testData2 = arrayOf("A", "B", "C")

        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, testData1)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, testData2)

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter1
        spinner2.adapter = adapter2

        // Crea un AlertDialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Crea el AlertDialog
        val alertDialog = builder.create()

        //GUARDAR Y CANCELAR PRODUCTO
        saveButton.setOnClickListener {

        }
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        // Muestra el AlertDialog
        alertDialog.show()
    }

}