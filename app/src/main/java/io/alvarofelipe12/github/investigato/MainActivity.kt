package io.alvarofelipe12.github.investigato

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.alvarofelipe12.github.investigato.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtraction.setOnClickListener(this)
        binding.btnMultiplication.setOnClickListener(this)
        binding.btnDivision.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val a = binding.etA.text.toString().toDouble()
        val b = binding.etB.text.toString().toDouble()
        var result = 0.0
        when(view?.id) {
            R.id.btn_add -> {
                result = a + b
            }
            R.id.btn_subtraction -> {
                result = a - b
            }
            R.id.btn_multiplication -> {
                result = a * b
            }
            R.id.btn_division -> {
                result = a / b
            }
        }
        binding.resultTv.text = getString(R.string.result_is, result.toString())
    }
}