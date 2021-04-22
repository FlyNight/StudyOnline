package com.example.studyonline

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.bean.LessonBean
import com.github.faucamp.simplertmp.RtmpHandler
import net.ossrs.yasea.SrsCameraView
import net.ossrs.yasea.SrsRecordHandler
import net.ossrs.yasea.SrsEncodeHandler
import net.ossrs.yasea.SrsPublisher
import java.io.IOException
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.net.SocketException


class LessonStartActivity :
    AppCompatActivity(),
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
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }

    private fun initTeacherView() {
        val cameraView: SrsCameraView = findViewById(R.id.live_video)
        val videoView: VideoView = findViewById(R.id.live_watch)
        cameraView.visibility = View.VISIBLE
        videoView.visibility = View.GONE
        val srsPublisher = SrsPublisher(cameraView)
        srsPublisher.setEncodeHandler(SrsEncodeHandler(this))
        srsPublisher.setRtmpHandler(RtmpHandler(this))
        srsPublisher.setRecordHandler(SrsRecordHandler(this))
        srsPublisher.setPreviewResolution(640,480)
        srsPublisher.setScreenOrientation(1)
        srsPublisher.setOutputResolution(640,480)
        srsPublisher.setVideoHDMode()
        srsPublisher.startCamera()
        srsPublisher.switchToHardEncoder()
        srsPublisher.startPublish("rtmp://82.156.194.22/$lessonId/livestream")
    }


    private fun initStudentView() {
        val cameraView: SrsCameraView = findViewById(R.id.live_video)
        val videoView: VideoView = findViewById(R.id.live_watch)
        cameraView.visibility = View.GONE
        videoView.visibility = View.VISIBLE
        videoView.setVideoPath("rtmp://82.156.194.22:1935/$lessonId/livestream")
    }

    override fun onRtmpConnecting(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onRtmpConnected(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onRtmpVideoStreaming() {
        TODO("Not yet implemented")
    }

    override fun onRtmpAudioStreaming() {
        TODO("Not yet implemented")
    }

    override fun onRtmpStopped() {
        TODO("Not yet implemented")
    }

    override fun onRtmpDisconnected() {
        TODO("Not yet implemented")
    }

    override fun onRtmpVideoFpsChanged(fps: Double) {
        TODO("Not yet implemented")
    }

    override fun onRtmpVideoBitrateChanged(bitrate: Double) {
        TODO("Not yet implemented")
    }

    override fun onRtmpAudioBitrateChanged(bitrate: Double) {
        TODO("Not yet implemented")
    }

    override fun onRtmpSocketException(e: SocketException?) {
        TODO("Not yet implemented")
    }

    override fun onRtmpIOException(e: IOException?) {
        TODO("Not yet implemented")
    }

    override fun onRtmpIllegalArgumentException(e: IllegalArgumentException?) {
        TODO("Not yet implemented")
    }

    override fun onRtmpIllegalStateException(e: IllegalStateException?) {
        TODO("Not yet implemented")
    }

    override fun onRecordPause() {
        TODO("Not yet implemented")
    }

    override fun onRecordResume() {
        TODO("Not yet implemented")
    }

    override fun onRecordStarted(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onRecordFinished(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onRecordIllegalArgumentException(e: IllegalArgumentException?) {
        TODO("Not yet implemented")
    }

    override fun onRecordIOException(e: IOException?) {
        TODO("Not yet implemented")
    }

    override fun onNetworkWeak() {
        TODO("Not yet implemented")
    }

    override fun onNetworkResume() {
        TODO("Not yet implemented")
    }

    override fun onEncodeIllegalArgumentException(e: IllegalArgumentException?) {
        TODO("Not yet implemented")
    }
}