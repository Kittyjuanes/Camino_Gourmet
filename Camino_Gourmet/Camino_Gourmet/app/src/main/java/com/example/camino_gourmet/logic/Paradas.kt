package com.example.camino_gourmet.logic

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.camino_gourmet.R
import com.example.camino_gourmet.data.Data
import com.example.camino_gourmet.data.Funciones
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Paradas: AppCompatActivity() {

    private lateinit var Restaurante: String
    private lateinit var statusTextView: TextView
    private  lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var listView: ListView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()*/
        setContentView(R.layout.paradas)


        val latitude = Data.latitude
        val longitude = Data.longitude
        val searchView = findViewById<SearchView>(R.id.searchview)
        val algo = Data.MY_PERMISSION_LOCATION_CODE
        listView = findViewById<ListView>(R.id.lista)
        statusTextView = findViewById(R.id.Ubicacion)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        Restaurante = intent.getStringExtra("TipoRestaurante") ?: ""
        Funciones.guardarRestaurantesjson(this, Restaurante)

        val adapter =  RestaurantsAdapter(this,Data.RESTAURANT_LIST)
        listView.adapter = adapter


        // Configurar el SearchView para que filtre los resultados
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtrar la lista cuando el texto cambie
                adapter.filter.filter(newText)
                return false
            }
        })

        statusTextView.text = "Ubicado en latitud $latitude y longitud $longitude"
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        */



    }


}