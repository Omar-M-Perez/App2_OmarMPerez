package com.example.pizza_create

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_startscreen.*

class startscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_startscreen)

        b1.setOnClickListener{
            val intent= Intent( this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}