package com.example.studyonline

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.bean.LessonBean
import com.github.faucamp.simplertmp.RtmpHandler
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.seu.magicfilter.utils.MagicFilterType
import net.ossrs.yasea.SrsCameraView
import net.ossrs.yasea.SrsEncodeHandler
import net.ossrs.yasea.SrsPublisher
import net.ossrs.yasea.SrsRecordHandler
import java.io.IOException
import java.io.Serializable
import java.net.SocketException


class LessonStartActivity :
    Activity(),
    RtmpHandler.RtmpListener,
    SrsRecordHandler.SrsRecordListener,
    SrsEncodeHandler.SrsEncodeListener {
    lateinit var lessonId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_start)
        val se: Serializable? = intent.getSerializableExtra("lesson")
        if (se is LessonBean) {
            var lesson: LessonBean = se
            lessonId = lesson.id.toString()
        }
        requestPermission()
        if (MainActivity.userIdentity == Identity.TEACHER.IDENTITY) {
            initTeacherView()
        }
        else {
            initStudentView()

        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 100
            )
        }
    }

    lateinit var srsPublisher: SrsPublisher
    private fun initTeacherView() {


        val cameraView: SrsCameraView = findViewById(R.id.live_video)
        val videoView: PlayerView = findViewById(R.id.live_watch)
        cameraView.visibility = View.VISIBLE
        videoView.visibility = View.GONE
        srsPublisher = SrsPublisher(cameraView)
        srsPublisher.setEncodeHandler(SrsEncodeHandler(this))
        srsPublisher.setRtmpHandler(RtmpHandler(this))
        srsPublisher.setRecordHandler(SrsRecordHandler(this))
        if (!cameraView.isEnabled) {
            Toast.makeText(applicationContext, "No Camera Found", Toast.LENGTH_SHORT).show()
            return
        }
        srsPublisher.setPreviewResolution(1280, 720)
        srsPublisher.setOutputResolution(720, 1280)
        srsPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
        srsPublisher.setVideoHDMode()
        srsPublisher.startCamera()
        srsPublisher.switchToHardEncoder()
        srsPublisher.startPublish("rtmp://82.156.194.22/$lessonId/livestream")
    }

    private fun initStudentView() {
        val cameraView: SrsCameraView = findViewById(R.id.live_video)
        val videoView: PlayerView = findViewById(R.id.live_watch)
        cameraView.visibility = View.GONE
        videoView.visibility = View.VISIBLE
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val player : SimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(applicationContext, trackSelector)
        videoView.player = player
        val rtmpDataSourceFactory = RtmpDataSourceFactory()
        val videoSource: MediaSource = ExtractorMediaSource.Factory(rtmpDataSourceFactory).createMediaSource(
            Uri.parse("rtmp://82.156.194.22/$lessonId/livestream"))
        player.prepare(videoSource)
        player.playWhenReady = true
    }

    override fun onRtmpConnecting(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRtmpConnected(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRtmpVideoStreaming() {

    }

    override fun onRtmpAudioStreaming() {

    }

    override fun onRtmpStopped() {
        Toast.makeText(applicationContext, "stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onRtmpDisconnected() {
        Toast.makeText(applicationContext, "disconnect", Toast.LENGTH_SHORT).show()
    }

    override fun onRtmpVideoFpsChanged(fps: Double) {

    }

    override fun onRtmpVideoBitrateChanged(bitrate: Double) {

    }

    override fun onRtmpAudioBitrateChanged(bitrate: Double) {

    }

    override fun onRtmpSocketException(e: SocketException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onRtmpIOException(e: IOException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onRtmpIllegalArgumentException(e: IllegalArgumentException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onRtmpIllegalStateException(e: IllegalStateException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onRecordPause() {
        Toast.makeText(applicationContext, "Record paused", Toast.LENGTH_SHORT).show();
    }

    override fun onRecordResume() {
        Toast.makeText(applicationContext, "Record resumed", Toast.LENGTH_SHORT).show();
    }

    override fun onRecordStarted(msg: String?) {
        Toast.makeText(applicationContext, "Recording file: $msg", Toast.LENGTH_SHORT).show();
    }

    override fun onRecordFinished(msg: String?) {
        Toast.makeText(applicationContext, "Recording file: $msg", Toast.LENGTH_SHORT).show();
    }

    override fun onRecordIllegalArgumentException(e: IllegalArgumentException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onRecordIOException(e: IOException?) {
        if (e != null) {
            handleException(e)
        }
    }

    override fun onNetworkWeak() {
        Toast.makeText(applicationContext, "网络信号弱", Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkResume() {

    }

    override fun onEncodeIllegalArgumentException(e: IllegalArgumentException?) {
        if (e != null) {
            handleException(e)
        }
    }

    private fun handleException(e: Exception) {
        try {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            srsPublisher.stopPublish()
            srsPublisher.stopRecord()
        } catch (e1: Exception) {
            //
        }
    }

}