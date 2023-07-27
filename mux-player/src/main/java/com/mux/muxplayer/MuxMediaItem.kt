package com.mux.muxplayer

import android.net.Uri
import androidx.media3.common.MediaItem

// TODO: Will likely need to use extensions here? (CJP)
typealias MuxMediaItem = MediaItem

// TODO: This is an inner class on exo MediaItem.Builder
// Ideally, our implementation would look and feel similar, but not sure
// if (and if so, how) we can do this given the non-interfaced final classes
// involved
class MuxMediaItemBuilder(var builder: MediaItem.Builder = MediaItem.Builder()) {
    // TODO: Ideally we wouldn't have this much redundant/boilerplate code
    // May be required for our extension plans (delegate by convention)
    fun setUri(uri: String?): MuxMediaItemBuilder {
        this.builder = this.builder.setUri(uri)
        return this
    }

    fun setUri(uri: Uri?): MuxMediaItemBuilder {
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