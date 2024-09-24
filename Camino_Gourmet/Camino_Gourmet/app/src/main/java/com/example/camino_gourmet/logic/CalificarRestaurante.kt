package com.example.camino_gourmet.logic

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.camino_gourmet.R

class CalificarRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificar_restaurante)

        val layout = findViewById<ConstraintLayout>(R.id.layoutSubirImagen)
        layout.setBackgroundResource(R.drawable.rounded_corners)
        layout.background.setTint(Color.parseColor("#f2f2f2"))

    }
}