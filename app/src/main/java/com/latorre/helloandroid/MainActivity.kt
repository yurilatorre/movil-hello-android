package com.latorre.helloandroid

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val btnSaludar: Button = findViewById(R.id.btnSaludar)

        btnSaludar.setOnClickListener {
            contador++
            textView.text = "Has hecho clic $contador veces"

            // Mostrar mensaje emergente
            Toast.makeText(
                this@MainActivity,
                "¡Botón presionado!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}