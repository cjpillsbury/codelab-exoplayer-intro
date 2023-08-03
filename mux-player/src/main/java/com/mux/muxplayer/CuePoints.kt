package com.mux.muxplayer

class CuePoints {
  val cuePoints = mutableListOf<CuePoint>()
}

data class CuePoint (
  val timeMillis: Long,
  val data: String
)