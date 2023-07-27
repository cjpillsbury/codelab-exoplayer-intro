package com.mux.muxplayer

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer

class MuxPlayer(val exoPlayer: ExoPlayer): ExoPlayer by exoPlayer {
    override fun release() {
        exoPlayer.release()
        // Do our own release stuff (NOTE: Doing after based on example order)
        // You can add your own data to a View, which will override any data we collect
//        val customerData = CustomerData(
//            CustomerPlayerData().apply { },
//            CustomerVideoData().apply {
//                title = "Mux Data SDK for Media3 Demo"
//            },
//            CustomerViewData().apply { }
//        )
//
//        val muxStats = player.monitorWithMuxData(
//            context = this,
//            envKey = MUX_DATA_ENV_KEY,
//            customerData = customerData,
//            playerView = view.playerView
//        )
//        // (beta-only) If you're playing from a MediaSessionService, you'll need to manually set screen & player size
//        muxStats.setPlayerSize(...)
//        muxStats.setScreenSize(...)

//        return muxStats
    }
    class Builder(private val context: Context) {
        fun build(): MuxPlayer {
//            val clazz = Class.forName("androidx.media3.exoplayer.hls.HlsMediaSource\$Factory").kotlin
//            val clazz = Class.forName("androidx.media3.exoplayer.ExoPlayerImpl").kotlin
//            println(clazz)
            println("I AM A MUXOPLAYER RAWR")
            val exoPlayerInstance = ExoPlayer.Builder(this.context).build()
            return MuxPlayer(exoPlayerInstance)
        }
    }
}