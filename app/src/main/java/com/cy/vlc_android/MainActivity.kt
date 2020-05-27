package com.cy.vlc_android

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.util.VLCVideoLayout
import org.videolan.libvlc.MediaPlayer
import java.io.IOException

class MainActivity : AppCompatActivity() {

//    private val VIDEO_URL = "rtsp://10.154.139.119/output.264"
    private val VIDEO_URL = "rtsp://admin:zxxd1001@192.168.1.63:554/Streaming/Channels/101"
    private val USE_TEXTURE_VIEW = false
    private val ENABLE_SUBTITLES = true

    private lateinit var mVideoLayout: VLCVideoLayout

    private lateinit var mLibVLC: LibVLC
    private lateinit var mMediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLibVLC = LibVLC(this, null)
        mMediaPlayer = MediaPlayer(mLibVLC)

        mVideoLayout = findViewById(R.id.video_layout)

    }

    override fun onStart() {
        super.onStart()
        mMediaPlayer.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW)

        try {
            mMediaPlayer.play(Uri.parse(VIDEO_URL))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        mMediaPlayer.stop();
        mMediaPlayer.detachViews();
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer.release();
        mLibVLC.release();
    }
}
