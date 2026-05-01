package com.example.seminarapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        val hp = intent.getStringExtra("hp")
        val gender = intent.getStringExtra("gender")
        val seminar = intent.getStringExtra("seminar")

        val hasil = """
            Nama: $nama
            Email: $email
            No HP: $hp
            Gender: $gender
            Seminar: $seminar
        """.trimIndent()

        tvHasil.text = hasil

        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}