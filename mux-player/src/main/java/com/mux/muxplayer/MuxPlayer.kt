package com.mux.muxplayer

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class MuxPlayer(val exoPlayer: ExoPlayer): ExoPlayer by exoPlayer {
    override fun release() {
        exoPlayer.release()
        // Do our own release stuff (NOTE: Doing after based on example order)
    }
    class Builder(private val context: Context) {
        private val builder: ExoPlayer.Builder = ExoPlayer.Builder(context)

        fun build(): MuxPlayer {
            println("I AM A MUXOPLAYER RAWR")
            val exoPlayerInstance = builder.build()
            return MuxPlayer(exoPlayerInstance)
        }
    }
}