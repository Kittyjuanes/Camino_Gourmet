package com.example.camino_gourmet.logic

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.camino_gourmet.R

class Perfil : AppCompatActivity() {
    lateinit var layoutInfoCuenta : RelativeLayout
    lateinit var layoutCambiarContrasena : RelativeLayout
    lateinit var layoutCerrarSesion : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        layoutInfoCuenta = findViewById<RelativeLayout>(R.id.layoutInfoCuenta)
        layoutCambiarContrasena = findViewById<RelativeLayout>(R.id.layoutContrasena)
        layoutInfoCuenta.setOnClickListener{clickInfoCuenta()}
        layoutCambiarContrasena.setOnClickListener{clickCambiarContrasena()}
        layoutCerrarSesion = findViewById<LinearLayout>(R.id.layoutCerrarSesion)
        layoutCerrarSesion.setOnClickListener{clickCerrarSesion()}


    }

    fun clickInfoCuenta(){
        var intentInfoCuenta = Intent(this, EditarCuenta::class.java)
        startActivity(intentInfoCuenta)
    }

    fun clickCambiarContrasena(){
        var intentContrasena = Intent(this, CambiarContrasena::class.java)
        startActivity(intentContrasena)
    }

    fun clickCerrarSesion(){
        var intentCerrarSesion = Intent(this, InicioSesion::class.java)
        startActivity(intentCerrarSesion)
    }
}