package io.alvarofelipe12.github.investigato.presentation

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.alvarofelipe12.github.investigato.R

class CustomYouTubePlayerUiController(
    private val youTubePlayerView: YouTubePlayerView,
    private val customUi: View
) : AbstractYouTubePlayerListener() {

    private var youTubePlayer: YouTubePlayer? = null
    private var isMuted = true

    init {
        val muteButton: ImageView = customUi.findViewById(R.id.iv_mute_button_youtube)
        muteButton.setOnClickListener {
            toggleMute()
        }
    }

    private fun toggleMute() {
        youTubePlayer?.let {
            val muteButton: ImageView = customUi.findViewById(R.id.iv_mute_button_youtube)
            if (isMuted) {
                it.unMute()
                muteButton.setImageResource(R.drawable.baseline_volume_up_24)
            } else {
                it.mute()
                muteButton.setImageResource(R.drawable.baseline_volume_off_24)
            }
            isMuted = !isMuted
        }
    }

    fun release() {
        youTubePlayerView.release()
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer = youTubePlayer
        youTubePlayer.loadVideo("S0Q4gqBUs7c", 0f) // Replace with your video ID
        youTubePlayer.mute()
        // Remove the custom UI view from its parent if it has one
        (customUi.parent as? ViewGroup)?.removeView(customUi)
        // Attach the custom UI
        youTubePlayerView.setCustomPlayerUi(customUi)
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        if (state == PlayerConstants.PlayerState.ENDED) {
            youTubePlayer.loadVideo("S0Q4gqBUs7c", 0f) // Replace with your video ID
        }
    }
}