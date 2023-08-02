package com.mux.muxplayer

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.upstream.CmcdConfiguration

class MuxPlayer private constructor(private val player: ExoPlayer) : Player by player {

  /**
   * Reference to the [ExoPlayer] that powers this `MuxPlayer` instance. In most cases, you
   * don't have to access this directly, but it's available if you need it
   *
   * No guarantees about the player's state are made, and methods that control playback, eg
   * [Player.addMediaItem], [Player.setPlayWhenReady], etc may not work as expected.
   */
  val exoPlayer by this::player

  //private val muxStats: MuxStatsSdkMedia3<ExoPlayer>

  fun setCuePoints(points: CuePoints) {
    // Just a for-instance. If we also offer tools to download CuePoints, then probs we'd set a
    // loader or "CuePoints ID" or something instead of making them set that up
  }

  override fun release() {
    player.release()
    // muxStats.release()
    // Do our own release stuff (NOTE: Doing after based on example order)
  }

  init {
    // muxStats = ...
  }

  class Builder(private val context: Context) {
    private var builder = ExoPlayer.Builder(context)

    // Manually delegate to the internal Exo Builder, for each method (sorry)

    // NOTE: Prioritizing this in POC for one route forward on CMCD support "out of box" (CJP
    @JvmOverloads
    fun setMediaSourceFactory(
      mediaSourceFactory: MediaSource.Factory,
      configureAutomatically: Boolean = true
    ): Builder {
      this.builder = this.builder.setMediaSourceFactory(mediaSourceFactory)
      // DESIGN NOTES:
      //  Conflicts in Dependency Injection:
      //  Resolving conflicts between customer-injected objects an our own is not
      //  a simple problem. We inject entire objects, not just methods. Two MediaSources, for
      //  instance, would each try to fetch the media. Obviously this is undesirable, regardless of
      //  the order they are called. There's one MediaSource, which can get many tracks, and that
      //  is media3's design.
      //  Luckily, the tradeoff of losing features because you injected a less-capable object
      //  of your own is a well-understood part of dependency injection. This resolution
      //  strategy (set our own stuff, but let them disable that) is entirely reasonable
      // DESIGN NOTES:
      //  Overloading and Defaults:
      //  This adds a parameter to the method, but doesn't break any integrations. In kotlin,
      //  the reasonable default is used. In java, overloads of this method are synthesized, that
      //  call this one with default params. `true` is the obvious choice for a default, as most
      //  people will either inject Exo's default objects or else they'll let Exo inject the defaults
      //  for them. This is fine, we are also injecting defaults, ergo `true` is safe most of the time
      if (configureAutomatically) {
        mediaSourceFactory.configureForMux()
      }
      return this
    }

    fun build(): MuxPlayer {
      println("I AM A MUXOPLAYER RAWR")

      val exoPlayerInstance = builder.build()
      return MuxPlayer(exoPlayerInstance)
    }

    init {
      builder.setMediaSourceFactory(Defaults.mediaSourceFactory(context))
      // TODO: Add more defaults, if nesc.
      // DESIGN NOTES: Exo injects default objects w/default configurations so only inject objects
      //  where we actually need to change something
    }

    /**
     * Factory for default player components
     */
    private companion object Defaults {

      @OptIn(UnstableApi::class)
      fun MediaSource.Factory.configureForMux(): MediaSource.Factory {
        setCmcdConfigurationFactory(CmcdConfiguration.Factory.DEFAULT)
        return this
      }

      @OptIn(UnstableApi::class)
      fun mediaSourceFactory(context: Context): MediaSource.Factory {
        return DefaultMediaSourceFactory(context).configureForMux()
      }
    }
  }
}