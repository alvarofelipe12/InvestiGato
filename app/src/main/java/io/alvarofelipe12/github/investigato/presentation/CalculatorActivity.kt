package io.alvarofelipe12.github.investigato.presentation

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import io.alvarofelipe12.github.investigato.MainActivity
import io.alvarofelipe12.github.investigato.R
import io.alvarofelipe12.github.investigato.databinding.ActivityCalculatorBinding
import io.alvarofelipe12.github.investigato.databinding.CustomYoutubeLayoutBinding
import java.text.NumberFormat
import java.util.Currency

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerOnline: ExoPlayer
    private lateinit var customOverlayBinding: CustomYoutubeLayoutBinding
    private lateinit var customYouTubePlayerUiController: CustomYouTubePlayerUiController
    private var isMuted = true
    private var isMutedOnline = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
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
        setLabelForSlider()
        setBarChart()
        setAssetsVideo()
        setOnlineVideo()
        setYoutubeVideo()

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

    private fun setAssetsVideo() {
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
            binding.ivMuteButton.contentDescription =
                if (isMuted) R.string.mute_button.toString() else R.string.unmute_button.toString()
        }
    }

    private fun setOnlineVideo() {
        playerOnline = ExoPlayer.Builder(this).build()
        binding.playerViewOnline.player = playerOnline
        Log.d("CustomLogs", "Online started")
        val uri = Uri.parse("https://videos.pexels.com/video-files/855401/855401-hd_1280_720_25fps.mp4")
        val mediaItem = MediaItem.fromUri(uri)
        playerOnline.setMediaItem(mediaItem)
        playerOnline.prepare()
        playerOnline.playWhenReady = true
        playerOnline.volume = 0f
        playerOnline.repeatMode = ExoPlayer.REPEAT_MODE_ONE

        binding.ivMuteButtonOnline.setOnClickListener {
            isMutedOnline = !isMutedOnline
            playerOnline.volume = if (isMutedOnline) 0f else 1f
            binding.ivMuteButtonOnline.setImageResource(if (isMutedOnline) R.drawable.baseline_volume_off_24 else R.drawable.baseline_volume_up_24)
            binding.ivMuteButtonOnline.contentDescription =
                if (isMutedOnline) R.string.mute_button.toString() else R.string.unmute_button.toString()
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

    private fun setYoutubeVideo() {
        val customUi = binding.youtubePlayerView.inflateCustomPlayerUi(R.layout.custom_youtube_layout)
        lifecycle.addObserver(binding.youtubePlayerView)

        customYouTubePlayerUiController = CustomYouTubePlayerUiController(
            binding.youtubePlayerView,
            customUi
        )

        binding.youtubePlayerView.addYouTubePlayerListener(customYouTubePlayerUiController)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        playerOnline.release()
        customYouTubePlayerUiController.release()
    }

    private fun setBarChart() {
        val barChart = binding.chart

        // Customize the X and Y axis
        val xAxis: XAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f

        val yAxis: YAxis = barChart.axisLeft
        yAxis.granularity = 1f
        barChart.axisRight.isEnabled = false

        // Remove the description label
        barChart.description.isEnabled = false

        // Hide the legend
        barChart.legend.isEnabled = false

        // Set the data for the chart
        val entries = listOf(
            BarEntry(0f, 5000f),
            BarEntry(1f, 6000f),
            BarEntry(2f, 7000f)
        )

        val dataSet = BarDataSet(entries, "")
        dataSet.color = Color.BLUE
        val barData = BarData(dataSet)

        barChart.data = barData
        barChart.invalidate() // Refresh the chart
    }
}