package com.example.studyonline.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.studyonline.R
import com.example.studyonline.data.bean.Outline

class LessonScheduleAdapter(activity: Activity, private val resourceId: Int, data: List<Outline>): ArrayAdapter<Outline>(activity, resourceId, data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val lessonScheduleStatus: ImageView = view.findViewById(R.id.lesson_schedule_status)
        val lessonScheduleName: TextView = view.findViewById(R.id.lesson_schedule_name)
        val outline = getItem(position)
        if (outline != null) {
            if(outline.done) {
                lessonScheduleStatus.setImageResource(R.mipmap.unlock)
            } else {
                lessonScheduleStatus.setImageResource(R.mipmap.lock)
            }
            lessonScheduleName.text = outline.name
        }
        return view
    }
}