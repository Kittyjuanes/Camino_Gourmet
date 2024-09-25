package com.example.camino_gourmet.logic

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.camino_gourmet.R

class Perfil : AppCompatActivity() {
    lateinit var layoutInfoCuenta : RelativeLayout
    lateinit var layoutCambiarContrasena : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        layoutInfoCuenta = findViewById<RelativeLayout>(R.id.layoutInfoCuenta)
        layoutCambiarContrasena = findViewById<RelativeLayout>(R.id.layoutContrasena)
        layoutInfoCuenta.setOnClickListener{clickInfoCuenta()}
        layoutCambiarContrasena.setOnClickListener{clickCambiarContrasena()}

    }

    fun clickInfoCuenta(){
        var intentInfoCuenta = Intent(this, EditarCuenta::class.java)
        startActivity(intentInfoCuenta)
    }

    fun clickCambiarContrasena(){
        var intentContrasena = Intent(this, CambiarContrasena::class.java)
        startActivity(intentContrasena)
    }
}