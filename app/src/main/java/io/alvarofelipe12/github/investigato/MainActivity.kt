package io.alvarofelipe12.github.investigato

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.alvarofelipe12.github.investigato.databinding.ActivityMainBinding
import io.alvarofelipe12.github.investigato.presentation.AboutUsActivity
import io.alvarofelipe12.github.investigato.presentation.CalculatorActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val COLORED_WORD_1 = "earnings"
        const val COLORED_WORD_2 = "times"
    }

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
        binding.btnLogin.setOnClickListener(this)
        binding.btnCalculator.setOnClickListener(this)
        binding.btnAboutUs.setOnClickListener(this)
        setStyledText()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_about_us -> {
                val intent = Intent(this, AboutUsActivity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.btn_calculator -> {
                val intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.btn_about_us -> {
//                val intent = Intent(this,AboutUsActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    private fun setStyledText() {
        val aquamarineColor = ContextCompat.getColor(this, R.color.aquamarine)

        val textInvestigato = getString(R.string.investigato_invest)
        val builder = SpannableStringBuilder(textInvestigato)

        applyColorSpan(builder, textInvestigato, COLORED_WORD_1, aquamarineColor)
        applyColorSpan(builder, textInvestigato, COLORED_WORD_2, aquamarineColor)

        binding.textViewInvestigato.text = builder
    }

    private fun applyColorSpan(
        spannableStringBuilder: SpannableStringBuilder,
        text: String,
        wordToBeColored: String,
        color: Int
    ) {
        val startWord = text.indexOf(wordToBeColored)
        val endWord = startWord + wordToBeColored.length
        if (startWord != -1) {
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(color),
                startWord,
                endWord,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}