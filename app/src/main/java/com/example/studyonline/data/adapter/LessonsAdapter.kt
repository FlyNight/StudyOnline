package com.example.studyonline.data.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.studyonline.activitys.LessonInformationActivity
import com.example.studyonline.activitys.LessonTeacherActivity
import com.example.studyonline.R
import com.example.studyonline.activitys.LessonStudentActivity
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.bean.LessonBean
import java.util.*

class LessonsAdapter(activity: Activity, private val resourceId: Int, data: List<LessonBean>) :
    ArrayAdapter<LessonBean>(activity, resourceId, data) {
    @SuppressLint("ViewHolder", "SimpleDateFormat")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val lessonName: TextView = view.findViewById(R.id.lesson_name)
        val lessonTeacher: TextView = view.findViewById(R.id.lesson_teacher)
        val lessonTime: TextView = view.findViewById(R.id.lesson_time)
        val lessonInformation: Button = view.findViewById(R.id.lesson_information)
        val lessonStart: Button = view.findViewById(R.id.lesson_start)
        val lesson = getItem(position)

        if (lesson != null) {
            lessonName.text = lesson.name
            lessonTeacher.text = lesson.teacher
            lessonTime.text = lesson.time
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss_E")
            var time: String = simpleDateFormat.format(Date(System.currentTimeMillis()))
            when (time.substring(9)) {
                "Mon" -> {
                    time = time.substring(0,9) + "1"
                }

                "Tue" -> {
                    time = time.substring(0,9) + "2"
                }

                "Wen" -> {
                    time = time.substring(0,9) + "3"
                }

                "Thu" -> {
                    time = time.substring(0,9) + "4"
                }

                "Fri" -> {
                    time = time.substring(0,9) + "5"
                }

                "Sat" -> {
                    time = time.substring(0,9) + "6"
                }

                "Sun" -> {
                    time = time.substring(0,9) + "7"
                }
            }
            if (time == lesson.time) {
                lessonInformation.visibility = View.VISIBLE
                lessonStart.visibility = View.GONE
            } else {
                lessonStart.visibility = View.VISIBLE
                lessonInformation.visibility = View.GONE
            }
            lessonInformation.setOnClickListener {
                context.startActivity(
                    Intent(
                        context,
                        LessonInformationActivity::class.java
                    ).putExtra("lesson", lesson)
                )
            }
            lessonStart.setOnClickListener {
                if (MainActivity.userIdentity == Identity.TEACHER.IDENTITY) {
                    context.startActivity(
                        Intent(
                            context,
                            LessonTeacherActivity::class.java
                        ).putExtra("lesson", lesson)
                    )
                }
                else if (MainActivity.userIdentity == Identity.STUDENT.IDENTITY) {
                    context.startActivity(
                        Intent(
                            context,
                            LessonStudentActivity::class.java
                        ).putExtra("lesson", lesson)
                    )
                }
            }
        }
        return view
    }
}