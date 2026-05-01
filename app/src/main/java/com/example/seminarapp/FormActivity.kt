package com.example.seminarapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_form)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etNoHp = findViewById<EditText>(R.id.etNoHp)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spinner = findViewById<Spinner>(R.id.spinnerSeminar)
        val cbSetuju = findViewById<CheckBox>(R.id.cbSetuju)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        val seminarList = arrayOf(
            "AI Seminar",
            "Web Development",
            "Cyber Security",
            "Mobile Development",
            "UI/UX Design"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seminarList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnSubmit.setOnClickListener {

            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val noHp = etNoHp.text.toString()

            if(nama.isEmpty()){
                etNama.error = "Nama wajib diisi"
                return@setOnClickListener
            }

            if(!email.contains("@")){
                etEmail.error = "Email tidak valid"
                return@setOnClickListener
            }

            if(noHp.length !in 10..13 || !noHp.startsWith("08")){
                etNoHp.error = "Nomor HP tidak valid"
                return@setOnClickListener
            }

            if(rgGender.checkedRadioButtonId == -1){
                Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!cbSetuju.isChecked){
                Toast.makeText(this, "Harus menyetujui data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "-"
            }

            val seminar = spinner.selectedItem.toString()

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data sudah benar?")
                .setPositiveButton("Ya") { _, _ ->

                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("nama", nama)
                    intent.putExtra("email", email)
                    intent.putExtra("hp", noHp)
                    intent.putExtra("gender", gender)
                    intent.putExtra("seminar", seminar)

                    startActivity(intent)
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}