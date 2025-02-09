package io.alvarofelipe12.github.investigato.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.alvarofelipe12.github.investigato.R
import io.alvarofelipe12.github.investigato.databinding.ActivityAboutUsBinding
import io.alvarofelipe12.github.investigato.databinding.ActivityCalculatorBinding
import java.text.NumberFormat
import java.util.Currency

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setLabelForSlider()
        setVideo()
        binding.sliderInvestment.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed

        }
    }

    private fun setLabelForSlider() {
        binding.sliderInvestment.setLabelFormatter { value: Float ->
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("USD")
            format.format(value.toDouble())
        }
    }

    private fun setVideo() {
        val path = "android.resource://"+ packageName + "/" + R.raw.cats
        Log.d("CustomLogs", path)
        binding.vvCatVideo.setVideoPath(path)
        binding.vvCatVideo
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.vvCatVideo)
        mediaController.setMediaPlayer(binding.vvCatVideo)
        mediaController.visibility = View.GONE //
        binding.vvCatVideo.setMediaController(mediaController)
        binding.vvCatVideo.start()
    }
}