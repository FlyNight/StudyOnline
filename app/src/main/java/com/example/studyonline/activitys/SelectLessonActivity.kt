package com.example.studyonline.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.studyonline.R
import com.example.studyonline.data.bean.LessonBean
import com.example.studyonline.ui.login.LoginActivity
import com.google.android.material.navigation.NavigationView
import java.io.Serializable
import java.sql.ResultSet
import kotlin.properties.Delegates

class SelectLessonActivity : AppCompatActivity() {

    private var lessonId by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_lesson)
        setSupportActionBar(findViewById(R.id.toolbar))
        val se: Serializable? = intent.getSerializableExtra("key")
        if(se is LessonBean) {
            val lesson: LessonBean =  se
            title = lesson.name
            lessonId = lesson.id
            initView(lesson)
        }
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (MainActivity.id == -1) {
                Snackbar.make(view, "Please Login First Then You can choose lessons", Snackbar.LENGTH_LONG)
                    .setAction("Go To Login", null).show()
            }else if (isTeacher(MainActivity.id)) {
                Snackbar.make(view, "Sorry, Teacher cannot choose lessons!", Snackbar.LENGTH_LONG)
                    .setAction("ACTION", null).show()
            }
            else if (isOwnLesson(lessonId, MainActivity.id)) {
                Snackbar.make(view, "You has this lesson already!", Snackbar.LENGTH_LONG)
                    .setAction("ACTION", null).show()
            } else {
                selectLesson(lessonId, MainActivity.id)
                Snackbar.make(view, "Select This Lesson Successful!", Snackbar.LENGTH_SHORT)
                    .setAction("ACTION", null).show()
            }
        }
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

    }


    @SuppressLint("SetTextI18n")
    private fun initView(lesson: LessonBean) {
        val optionalLessonOverview: TextView = findViewById(R.id.optional_lesson_overview)
        val optionalLessonTeacher: TextView = findViewById(R.id.optional_lesson_teacher)
        val optionalLessonTime: TextView = findViewById(R.id.optional_lesson_time)
        optionalLessonOverview.text = lesson.overview
        optionalLessonTeacher.text = lesson.teacher
        when(lesson.time.last().toInt()) {
            49 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周一 " +
                        lesson.time.substring(0,5)
            }
            50 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周二 " +
                        lesson.time.substring(0,5)
            }
            51 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周三 " +
                        lesson.time.substring(0,5)
            }
            52 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周四 " +
                        lesson.time.substring(0,5)
            }
            53 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周五 " +
                        lesson.time.substring(0,5)
            }
            54 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周六 " +
                        lesson.time.substring(0,5)
            }
            55 -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 周日 " +
                        lesson.time.substring(0,5)
            }
            else -> {
                optionalLessonTime.text = "第${lesson.startWeek}周 ~ 第${lesson.startWeek + lesson.duration}周" +
                        " 未知 " +
                        lesson.time.substring(0,5)
            }
        }
    }

    private fun isTeacher(userId: Int): Boolean {
        var flag = false
        val thread = Thread {
            val ps = MainActivity.cn.createStatement()
            val resultSet = ps.executeQuery("SELECT * FROM users_identitys WHERE user_id = $userId")
            flag = if (resultSet.next()) {
                resultSet.getInt("identity_id") == 1
            } else {
                Toast.makeText(applicationContext, "Invalid User!!!!", Toast.LENGTH_SHORT).show()
                true
            }
            ps.close()
        }
        thread.start()
        thread.join()
        return flag
    }
    private fun isOwnLesson(lessonId: Int, userId: Int): Boolean {
        var flag = true
        val thread =  Thread {
            val ps = MainActivity.cn.createStatement()
            val resultSet = ps.executeQuery("SELECT * FROM users_lessons WHERE user_id = $userId AND lesson_id = $lessonId")
            flag = resultSet.next()
            ps.close()
        }
        thread.start()
        thread.join()
        return flag
    }
    private fun selectLesson(lessonId: Int, userId: Int) {
        val thread = Thread {
            val ps = MainActivity.cn.prepareStatement("INSERT INTO  users_lessons(user_id, lesson_id) VALUES (?, ?)")
            ps.setInt(1, userId)
            ps.setInt(2,lessonId)
            ps.executeUpdate()
            ps.close()
        }
        thread.start()
        thread.join()
    }
}