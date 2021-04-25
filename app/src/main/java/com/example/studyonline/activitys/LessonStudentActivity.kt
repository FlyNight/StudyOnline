package com.example.studyonline.activitys

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.studyonline.R
import com.pedro.vlc.VlcListener
import com.pedro.vlc.VlcVideoLibrary

class LessonStudentActivity : AppCompatActivity(), VlcListener,
    View.OnClickListener {
    private lateinit var vlcVideoLibrary: VlcVideoLibrary
    private lateinit var bStartStop: Button
    private lateinit var etEndpoint: EditText
    private val options = arrayOf(":fullscreen")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_lesson_student)
        requestPermission()
        val surfaceView: SurfaceView = findViewById(R.id.surfaceView)
        bStartStop = findViewById(R.id.b_start_stop)
        bStartStop.setOnClickListener(this)
        etEndpoint = findViewById(R.id.et_endpoint)
        vlcVideoLibrary = VlcVideoLibrary(this, this, surfaceView)
        vlcVideoLibrary.setOptions(listOf(*options))
    }

    override fun onComplete() {
        Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show()
        vlcVideoLibrary.stop()
        bStartStop.text = getString(R.string.start_player)
    }

    override fun onClick(view: View) {
        if (!vlcVideoLibrary.isPlaying) {
            vlcVideoLibrary.play(etEndpoint.text.toString())
            bStartStop.text = getString(R.string.stop_player)
        } else {
            vlcVideoLibrary.stop()
            bStartStop.text = getString(R.string.start_player)
        }
    }

    private fun requestPermission() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            )
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET
                ), 100
            )
        }
    }
}