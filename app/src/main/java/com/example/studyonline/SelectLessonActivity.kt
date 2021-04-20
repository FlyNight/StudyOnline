package com.example.studyonline

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.studyonline.data.bean.LessonBean
import java.io.Serializable

class SelectLessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_lesson)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val se: Serializable? = intent.getSerializableExtra("key")
        if(se is LessonBean) {
            val lesson: LessonBean =  se
            title = lesson.name
            initView(lesson)
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
}