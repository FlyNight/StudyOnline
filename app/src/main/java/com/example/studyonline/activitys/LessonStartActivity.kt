package com.example.studyonline.activitys

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.PowerManager
import android.view.SurfaceView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.adapter.TalkAdapter
import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.bean.LessonBean
import com.example.studyonline.data.bean.TalkBean
import com.github.faucamp.simplertmp.RtmpHandler
import com.pedro.vlc.VlcListener
import com.pedro.vlc.VlcVideoLibrary
import com.seu.magicfilter.utils.MagicFilterType
import net.ossrs.yasea.SrsCameraView
import net.ossrs.yasea.SrsEncodeHandler
import net.ossrs.yasea.SrsPublisher
import net.ossrs.yasea.SrsRecordHandler
import java.io.IOException
import java.io.Serializable
import java.net.SocketException
import java.sql.Statement
import java.util.*
import kotlin.collections.ArrayList


class LessonStartActivity :
    Activity(),
    RtmpHandler.RtmpListener,
    SrsRecordHandler.SrsRecordListener,
    SrsEncodeHandler.SrsEncodeListener,
    VlcListener{
    lateinit var lessonId: String
    lateinit var lessonName: String
    lateinit var srsPublisher: SrsPublisher
    lateinit var vlcVideoLibrary: VlcVideoLibrary


    private lateinit var  ps: Statement
    private lateinit var talkList: RecyclerView
    private lateinit var message: EditText
    private lateinit var delayToShow: Button
    private val options = arrayOf(":fullscreen")

    @SuppressLint("WakelockTimeout")
    override fun onCreate(savedInstanceState: Bundle?) {
        ps = MainActivity.cn.createStatement()
        super.onCreate(savedInstanceState)
        val se: Serializable? = intent.getSerializableExtra("lesson")
        if (se is LessonBean) {
            val lesson: LessonBean = se
            lessonId = lesson.id.toString()
            lessonName = lesson.name
        }
        requestPermission()
        val powerManager: PowerManager = getSystemService(POWER_SERVICE) as PowerManager;
        val wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "myTag:wakeLock")
        wakeLock.acquire()
        if (MainActivity.userIdentity == Identity.TEACHER.IDENTITY) {
            initTeacherView()
        } else if (MainActivity.userIdentity == Identity.STUDENT.IDENTITY) {
            initStudentView()
        }
        initTalkView()
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
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

    private fun initTeacherView() {
        val message = Message()
        message.what = 3
        handler.sendMessage(message)
        setContentView(R.layout.activity_lesson_teacher)
        val cameraView: SrsCameraView = findViewById(R.id.live_video)
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
        srsPublisher.startAudio()
        srsPublisher.switchToHardEncoder()
        srsPublisher.startPublish("rtmp://82.156.194.22/$lessonId/livestream")
    }

    private fun initStudentView() {
        setContentView(R.layout.activity_lesson_student)
        val surfaceView: SurfaceView = findViewById(R.id.live_video)
        delayToShow = findViewById(R.id.delay_to_show)
        delayToShow.setOnClickListener {
            if (!vlcVideoLibrary.isPlaying) {
                vlcVideoLibrary.play("rtmp://82.156.194.22/$lessonId/livestream")
            } else {
                vlcVideoLibrary.stop()
            }
        }
        vlcVideoLibrary = VlcVideoLibrary(this, this, surfaceView)
        vlcVideoLibrary.setOptions(listOf(*options))
        Thread {
            Thread.sleep(500)
            val message = Message()
            message.what = 4
            handler.sendMessage(message)
        }.start()
    }

    private fun initTalkView() {
        talkList = findViewById(R.id.talk_list)
        message = findViewById(R.id.talk_edit)
        runOnUiThread {
            Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val message = Message()
                    message.what = 1
                    handler.sendMessage(message)
                }
            }, 0, 3000)

        }

        val send: Button = findViewById(R.id.talk_send)
        send.setOnClickListener {
            val message = Message()
            message.what = 2
            handler.sendMessage(message)
        }
    }

    override fun onDestroy() {
        ps.close()
        if (MainActivity.userIdentity == Identity.TEACHER.IDENTITY) {
            srsPublisher.stopPublish()
            srsPublisher.stopAudio()
            srsPublisher.stopCamera()

        }
        else if(MainActivity.userIdentity == Identity.STUDENT.IDENTITY) {
            vlcVideoLibrary.stop()
        }
        super.onDestroy()
    }

    @SuppressLint("SimpleDateFormat")
    private val handler = Handler() {
        when (it.what) {
            1 -> {
                var adapter: TalkAdapter? = null
                val t1 = Thread {
                    val data: ArrayList<TalkBean> = ArrayList()
                    val resultSet =
                        ps.executeQuery("SELECT * FROM talks WHERE lesson_id = $lessonId")
                    while (resultSet.next()) {
                        data.add(
                            TalkBean(
                                resultSet.getString("lesson_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("current_time_"),
                                resultSet.getString("message")
                            )
                        )
                    }
                    adapter = TalkAdapter(applicationContext, data)
                }
                if (!ps.isClosed) {
                    t1.start()
                    t1.join()
                    val linearLayoutManager = LinearLayoutManager(applicationContext)
                    talkList.layoutManager = linearLayoutManager
                    talkList.adapter = adapter
                }
            }
            2 -> {
                message.isEnabled = false
                val t2 = Thread {
                    val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
                    val time: String = simpleDateFormat.format(Date(System.currentTimeMillis()))
                    val sql =
                        "INSERT INTO talks(lesson_id, user_name, current_time_, message) VALUES (?,?,?,?)"
                    val psmt = MainActivity.cn.prepareStatement(sql)
                    psmt.setString(1, lessonId)
                    psmt.setString(2, MainActivity.userName)
                    psmt.setString(3, time)
                    psmt.setString(4, message.text.toString())
                    psmt.executeUpdate()
                    psmt.close()
                }
                if (message.text.toString() != "") {
                    t2.start()
                    t2.join()
                    message.text = null
                    message.hint = resources.getString(R.string.todo)
                    message.isEnabled = true
                } else {
                    Toast.makeText(applicationContext, "发送内容为空", Toast.LENGTH_SHORT).show()
                }
            }
            3 -> {
                val t3 = Thread {
                    ps.execute("DELETE FROM  talks WHERE lesson_id = $lessonId")
                }
                t3.start()
                t3.join()
            }
            4 -> {
                delayToShow.performClick()
            }
        }
        true
    }

    //this below is for rtmp push
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

    //rtmp pull
    override fun onComplete() {
        Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show()
        vlcVideoLibrary.stop()
    }
}