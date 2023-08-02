package com.mux.muxplayer

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes

//object MuxMediaItemFactory {
//    fun createMuxMediaItem(assetId: String, customDomain: String?): MediaItem {
//        // TODO: real impl
//        return MediaItem.Builder().build()
//    }
//}
//fun MediaItem.Builder.setPlaybackId(playbackId: String?): MediaItem.Builder {
//    return MuxMediaItemBuilder(this).setPlaybackId(…).builder
//}

// TODO: Will likely need to use extensions here? (CJP)
typealias MuxMediaItem = MediaItem

const val MUX_DOMAIN = "mux.com"
// TODO: This is an inner class on exo MediaItem.Builder
// Ideally, our implementation would look and feel similar, but not sure
// if (and if so, how) we can do this given the non-interfaced final classes
// involved
class MuxMediaItemBuilder(var builder: MediaItem.Builder = MediaItem.Builder()) {
    private var customDomain: String? = null
    private var playbackId: String? = null
    private val muxUri: String?
        get() = if (this.playbackId != null) "https://stream.${this.customDomain ?: MUX_DOMAIN}/${this.playbackId}.m3u8" else null

    fun setPlaybackId(playbackId: String?): MuxMediaItemBuilder {
        if (playbackId === this.playbackId) return this
        this.playbackId = playbackId
        if (this.playbackId != null) {
            this.setMimeType(MimeTypes.APPLICATION_M3U8)
        }
        this.builder = this.builder.setUri(muxUri)
        return this
    }

    fun setCustomDomain(customDomain: String?): MuxMediaItemBuilder {
        if (customDomain == this.customDomain) return this
        this.customDomain = customDomain
        this.builder = this.builder.setUri(muxUri)
        return this
    }

    // TODO: Ideally we wouldn't have this much redundant/boilerplate code
    // May be required for our extension plans (delegate by convention)
    fun setUri(uri: String?): MuxMediaItemBuilder {
        this.playbackId = null
        this.builder = this.builder.setUri(uri)
        return this
    }

    fun setUri(uri: Uri?): MuxMediaItemBuilder {
        this.playbackId = null
        this.builder = this.builder.setUri(uri)
        return this
    }

    fun setMimeType(mimeType: String?): MuxMediaItemBuilder {
        this.builder = this.builder.setMimeType(mimeType)
        return this
    }

    fun build(): MediaItem {
        println("I AM A MUXIAITEM RAWR")
        return builder.build()
    }
}