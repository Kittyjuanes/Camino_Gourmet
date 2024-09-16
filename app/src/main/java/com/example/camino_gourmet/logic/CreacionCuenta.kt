package com.example.camino_gourmet.logic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.camino_gourmet.R

class CreacionCuenta: AppCompatActivity() {

    private lateinit var switch: Switch
    private lateinit var restaurante: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()*/
        setContentView(R.layout.creacion_cuenta)

        //Inicializacion de vistas
        restaurante = findViewById<EditText>(R.id.Restaurante)
        switch = findViewById<Switch>(R.id.Switch)
        val TextView = findViewById<TextView>(R.id.InicioSesion)
        val Button = findViewById<Button>(R.id.BotonIngreso)
        val nombre = findViewById<EditText>(R.id.NomUsuario)

        //Estado inicial del TextView Restaurante
        restaurante.visibility = View.GONE

        //Crear listener para el switch
        switch.setOnClickListener { view ->
            handleSwitchClick(view)
        }

        //Crear listener para cuando se haga click en el TextView
        TextView.setOnClickListener {
            val intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }

        Button.setOnClickListener {
            val nombre = nombre.text.toString()

            if(nombre.isNotEmpty()){
                val intent = Intent(this, Opciones::class.java)
                startActivity(intent)
            }

            else
                Toast.makeText(this,"Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show()


        }



            /*
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            */

    }


    private fun handleSwitchClick(view: View) {
        if (view.id == R.id.Switch) {
            //Mostrar u ocultar el TextView segun el estado del Switch
            if (switch.isChecked)
                restaurante.visibility = View.VISIBLE

            else
                restaurante.visibility = View.GONE
        }
    }

}

