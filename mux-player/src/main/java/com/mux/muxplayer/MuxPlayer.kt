package com.mux.muxplayer

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource

class MuxPlayer private constructor(private val player: ExoPlayer): Player by player {

  /**
   * Reference to the [ExoPlayer] that powers this `MuxPlayer` instance. In most cases, you
   * don't have to access this directly, but it's available if you need it
   *
   * No guarantees about the player's state are made, and methods that control playback, eg
   * [Player.addMediaItem], [Player.setPlayWhenReady], etc may not work as expected.
   */
    val exoPlayer by this::player

    //private val muxStats: MuxStatsSdkMedia3<ExoPlayer>

    override fun release() {
        player.release()
        // muxStats.release()
        // Do our own release stuff (NOTE: Doing after based on example order)
    }

    init {
      // muxStats = ...
    }

    class Builder(private val context: Context) {
        private var builder: ExoPlayer.Builder = ExoPlayer.Builder(context)

      // Manually delegate to the internal Exo Builder, for each method (sorry)

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