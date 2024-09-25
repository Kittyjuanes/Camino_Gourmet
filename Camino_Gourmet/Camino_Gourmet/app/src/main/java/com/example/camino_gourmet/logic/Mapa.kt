package com.example.camino_gourmet.logic

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camino_gourmet.R
import com.example.camino_gourmet.data.Data
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Mapa: AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private  lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var Restaurante: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()*/
        setContentView(R.layout.mapa)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val algo = Data.MY_PERMISSION_LOCATION_CODE

        statusTextView = findViewById(R.id.textView5)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Recibir el tipo de restaurante seleccionado
        Restaurante = intent.getStringExtra("TipoRestaurante") ?: ""
        val Button = findViewById<Button>(R.id.button)


        pedirPermiso(this, Manifest.permission.ACCESS_FINE_LOCATION, "Acceder Ubicacion", algo)
        pedirPermiso(this, Manifest.permission.ACCESS_COARSE_LOCATION, "Acceder Ubicacion", algo)


            /*
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            */

        Button.setOnClickListener {
            val intent = Intent(this, Paradas::class.java).apply {
                putExtra("TipoRestaurante", Restaurante)
            }
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intentCuenta = Intent(this, Perfil::class.java)
        var intentMiRestaurante = Intent(this, MiRestaurante::class.java)
        when(item.itemId){
            R.id.Cuenta -> startActivity(intentCuenta)
            R.id.miRestaurante -> startActivity(intentMiRestaurante)
            R.id.Inicio -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun pedirPermiso(context: Activity, permiso: String, justificacion: String, idCode: Int) {
        when {
            ContextCompat.checkSelfPermission(context, permiso) == PackageManager.PERMISSION_GRANTED -> {
                // Permiso ya concedido, usar la ubicación
                setLocation()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(context, permiso) -> {
                // Mostrar justificación y solicitar permiso
                showPermissionRationale()
                ActivityCompat.requestPermissions(context, arrayOf(permiso), idCode)
            }
            else -> {
                // Solicitar permiso directamente
                ActivityCompat.requestPermissions(this, arrayOf(permiso), idCode)
            }
        }
    }


    private fun setLocation() {
        // Verifica permisos antes de intentar acceder a la ubicación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mFusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {
                BuscarRestaurante(location)
                } else {
                    statusTextView.text = "No se pudo obtener la ubicación."
                    statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                }
            }
        } else {
            // Mostrar estado de permiso denegado
            showPermissionStatus(false)
        }
    }

    private fun BuscarRestaurante(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude

        Data.latitude = latitude
        Data.longitude = longitude

        // Mostrar la longitud y latitud en el TextView
        statusTextView.text = "Buscando restaurantes de $Restaurante en \n Longitud: $longitude \n Latitud: $latitude "
        statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))

        // Aquí deberías implementar la lógica para buscar restaurantes cercanos de acuerdo al tipo seleccionado
        // Puedes usar una API de terceros como Google Places o una base de datos local para hacer la búsqueda.

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCode -> {
                // Si el permiso fue cancelado, el arreglo de permisos esta vacio
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // El permiso fue concedido, usar ubicacion
                    setLocation()
                } else {
                    // Mostrar estado de permiso denegado
                    showPermissionStatus(false)
                }
                return
            } else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun showPermissionRationale() {
        Toast.makeText(this,"El permiso de ubicacion es necesario para acceder a tu longitud y latitud",
        Toast.LENGTH_LONG).show()
    }

    private fun showPermissionStatus(granted: Boolean) {
        if (!granted) {
            statusTextView.text = "PERMISO DENEGADO!"
            statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        } else {
            statusTextView.text = ""  // Limpiar el mensaje cuando se cargan los contactos
        }
    }


}

