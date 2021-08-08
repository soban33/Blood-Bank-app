package com.soban.bepositivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val card_finddonor = findViewById<CardView>(R.id.card_finddonor)
        val card_checkbank = findViewById<CardView>(R.id.card_checkbank)
        val card_donateblood = findViewById<CardView>(R.id.card_donateblood)

        card_finddonor.setOnClickListener {
            val intent = Intent(this,FillTheForm::class.java)
            intent.putExtra("donorORbank","donor")
            startActivity(intent)
        }
        card_checkbank.setOnClickListener {
            val intent = Intent(this,FillTheForm::class.java)
            intent.putExtra("donorORbank","bank")
            startActivity(intent)
        }

        card_donateblood.setOnClickListener {
            val intent = Intent(this,BecomeDonor::class.java)
            startActivity(intent)
        }

    }
}