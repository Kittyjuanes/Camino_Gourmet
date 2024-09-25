package com.example.camino_gourmet.logic

import ComentariosAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camino_gourmet.R
import com.example.camino_gourmet.data.Comentario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class PerfilRestaurante : AppCompatActivity() {
    private lateinit var comentariosView: RecyclerView
    private lateinit var adapter: ComentariosAdapter
    lateinit var botonCalificarRestaurante: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_restaurante)
        comentariosView = findViewById(R.id.comentariosView)
        comentariosView.layoutManager = LinearLayoutManager(this)
        loadComentarios()
        botonCalificarRestaurante = findViewById<Button>(R.id.botonCalificarRestaurante)
        botonCalificarRestaurante.setOnClickListener{clickBotonCalificarRestaurante()}

    }

    fun clickBotonCalificarRestaurante(){
        var intentCalificar = Intent(this, CalificarRestaurante::class.java)
        startActivity(intentCalificar)
    }

    private fun loadComentarios() {
        val gson = Gson()
        try {
            val reader = InputStreamReader(assets.open("comentarios.json"))
            val comentarioListType = object : TypeToken<List<Comentario>>() {}.type
            val comentarioResponse = gson.fromJson(reader, ComentarioResponse::class.java)
            adapter = ComentariosAdapter(comentarioResponse.comentarios)
            comentariosView.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private data class ComentarioResponse(
        val comentarios: List<Comentario>
    )
}