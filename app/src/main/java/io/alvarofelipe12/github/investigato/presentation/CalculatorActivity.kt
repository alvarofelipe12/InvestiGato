package io.alvarofelipe12.github.investigato.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import io.alvarofelipe12.github.investigato.MainActivity
import io.alvarofelipe12.github.investigato.R
import io.alvarofelipe12.github.investigato.databinding.ActivityAboutUsBinding
import io.alvarofelipe12.github.investigato.databinding.ActivityCalculatorBinding
import java.text.NumberFormat
import java.util.Currency

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var player: ExoPlayer
    private lateinit var muteButton: ImageView
    private var isMuted = true

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
        // Handle back press using OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("CustomLogs", "Handle the back button press")
                val intent = Intent(this@CalculatorActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        })
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
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
        val path = "android.resource://$packageName/${R.raw.cats}"
        Log.d("CustomLogs", path)
        val uri = Uri.parse(path)
        val mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
        player.volume = 0f
        player.repeatMode = ExoPlayer.REPEAT_MODE_ONE

        binding.ivMuteButton.setOnClickListener {
            isMuted = !isMuted
            player.volume = if (isMuted) 0f else 1f
            binding.ivMuteButton.setImageResource(if (isMuted) R.drawable.baseline_volume_off_24 else R.drawable.baseline_volume_up_24)
            binding.ivMuteButton.contentDescription = if (isMuted) R.string.mute_button.toString() else R.string.unmute_button.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the "Up" button click
                Log.d("CustomLogs", "Handle the \"Up\" button click")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}