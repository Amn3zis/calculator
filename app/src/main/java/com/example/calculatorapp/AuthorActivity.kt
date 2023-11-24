package com.example.calculatorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityAuthorBinding

class AuthorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonBackToCalculator.setOnClickListener {
            // Возвращение на экран калькулятора
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
