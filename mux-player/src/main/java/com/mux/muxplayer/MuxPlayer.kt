package com.mux.muxplayer

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource

class MuxPlayer(val exoPlayer: ExoPlayer): ExoPlayer by exoPlayer {
    override fun release() {
        exoPlayer.release()
        // Do our own release stuff (NOTE: Doing after based on example order)
        // Example: clean up mux stats crud here (CJP)
    }
    class Builder(private val context: Context) {
        private var builder: ExoPlayer.Builder = ExoPlayer.Builder(context)

        // NOTE: Prioritizing this in POC for one route forward on CMCD support "out of box" (CJP
        fun setMediaSourceFactory(mediaSourceFactory: MediaSource.Factory): Builder {
            this.builder = this.builder.setMediaSourceFactory(mediaSourceFactory)
            // Also do the default cmcd crud here
            return this
        }
        fun build(): MuxPlayer {
            println("I AM A MUXOPLAYER RAWR")
            val exoPlayerInstance = builder.build()
            return MuxPlayer(exoPlayerInstance)
        }
    }
}