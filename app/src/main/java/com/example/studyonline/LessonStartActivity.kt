package com.example.studyonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import com.example.studyonline.data.bean.Identity
import com.github.faucamp.simplertmp.RtmpHandler
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.net.SocketException


class LessonStartActivity : AppCompatActivity(), RtmpHandler.RtmpListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_start)
        if (MainActivity.userIdentity == Identity.TEACHER.IDENTITY) {
            initTeacherView()
        }
        else {
            initStudentView()
        }
    }
    private fun initTeacherView() {
    }
    private fun initStudentView() {

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
}