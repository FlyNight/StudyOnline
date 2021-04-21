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
import com.example.studyonline.LessonInformationActivity
import com.example.studyonline.LessonStartActivity
import com.example.studyonline.R
import com.example.studyonline.data.bean.LessonBean
import java.util.*

class LessonsAdapter(activity: Activity, private val resourceId: Int, data: List<LessonBean>): ArrayAdapter<LessonBean>(activity, resourceId, data) {
    @SuppressLint("ViewHolder", "SimpleDateFormat")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val lessonName: TextView = view.findViewById(R.id.lesson_name)
        val lessonTeacher: TextView = view.findViewById(R.id.lesson_teacher)
        val lessonTime: TextView = view.findViewById(R.id.lesson_time)
        val lessonInformation: Button = view.findViewById(R.id.lesson_information)
        val lessonStart: Button = view.findViewById(R.id.lesson_start)
        val lesson = getItem(position)
        if(lesson != null) {
            lessonName.text = lesson.name
            lessonTeacher.text = lesson.teacher
            lessonTime.text = lesson.time
            val simpleDateFormat =  SimpleDateFormat("HH:mm_E")
            val time: String = simpleDateFormat.format(Date(System.currentTimeMillis()))
            if(time == lesson.time) {
                lessonInformation.visibility = View.GONE
                lessonStart.visibility = View.VISIBLE
            } else {
                lessonStart.visibility = View.GONE
                lessonInformation.visibility = View.VISIBLE
            }
            lessonInformation.setOnClickListener {
                context.startActivity(Intent(context, LessonInformationActivity::class.java).putExtra("lesson", lesson))
            }
            lessonStart.setOnClickListener {
                context.startActivity(Intent(context, LessonStartActivity::class.java).putExtra("lesson", lesson))
            }
        }
        return view
    }
}