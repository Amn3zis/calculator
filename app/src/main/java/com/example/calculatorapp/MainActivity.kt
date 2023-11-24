package com.example.calculatorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState != null) {
            operand1 = savedInstanceState.getDouble("operand1")
            operand2 = savedInstanceState.getDouble("operand2")
            operator = savedInstanceState.getChar("operator")
            updateResult()
        }

        binding.buttonAdd.setOnClickListener { calculate('+') }
        binding.buttonSubtract.setOnClickListener { calculate('-') }
        binding.buttonMultiply.setOnClickListener { calculate('*') }
        binding.buttonDivide.setOnClickListener { calculate('/') }

        binding.buttonAboutAuthor.setOnClickListener {
            val intent = Intent(this, AuthorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("operand1", operand1)
        outState.putDouble("operand2", operand2)
        outState.putChar("operator", operator)
    }

    private fun calculate(newOperator: Char) {
        operand1 = binding.editTextOperand1.text.toString().toDoubleOrNull() ?: 0.0
        operand2 = binding.editTextOperand2.text.toString().toDoubleOrNull() ?: 0.0
        operator = newOperator
        updateResult()
    }

    private fun updateResult() {
        val result = when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            '*' -> operand1 * operand2
            '/' -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> Double.NaN
        }

        val resultText = "$operand1 $operator $operand2 = ${
            if (result.isNaN()) "Ошибка"
            else String.format("%.2f", result)
        }"
        binding.textViewResult.text = "Результат: $resultText"
    }
}
