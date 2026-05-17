package com.dazai.player.playback

import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.Futures

class MusicService : MediaSessionService() {

    private var mediaSession: MediaSession? = null
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        // إعداد مشغل الصوت الاحترافي ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()
        
        // ربط المشغل بالجلسة (Session) ليتحكم به الهاتف والاشعارات
        mediaSession = MediaSession.Builder(this, exoPlayer).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}
