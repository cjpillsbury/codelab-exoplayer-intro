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

// TODO: Don't use this
// TODO: Will likely need to use extensions here? (CJP)
typealias MuxMediaItem = MediaItem

const val MUX_DOMAIN = "mux.com"
