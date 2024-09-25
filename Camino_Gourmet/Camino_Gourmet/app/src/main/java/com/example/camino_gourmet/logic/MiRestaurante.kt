package com.example.camino_gourmet.logic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.camino_gourmet.R

class MiRestaurante : AppCompatActivity() {
    lateinit var switchRestaurante : Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_restaurante)
        switchRestaurante = findViewById<Switch>(R.id.switchRestaurante)
        checkSwitch()
        switchRestaurante.setOnClickListener{clickSwitch();checkSwitch()}
    }

    fun clickSwitch(){
        var intentHuella = Intent(this, AutorizarHuella::class.java)
        startActivity(intentHuella)
    }

    fun checkSwitch(){
        var stateChecked = switchRestaurante.isChecked
        if(stateChecked){
            switchRestaurante.text = "Abierto"
            switchRestaurante.setTextColor(resources.getColor(R.color.verde))
        }else{
            switchRestaurante.text = "Cerrado"
            switchRestaurante.setTextColor(resources.getColor(R.color.rojo))
        }
    }
}