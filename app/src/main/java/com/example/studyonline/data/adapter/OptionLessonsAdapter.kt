package com.example.studyonline.data.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.studyonline.R
import com.example.studyonline.activitys.SelectLessonActivity
import com.example.studyonline.data.bean.LessonBean

class OptionLessonsAdapter(activity: Activity, private val resourceId: Int, data: List<LessonBean>): ArrayAdapter<LessonBean>(activity, resourceId, data) {
    @SuppressLint("ViewHolder", "StringFormatMatches")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val tag: TextView = view.findViewById(R.id.tag)
        val optionLessonName: TextView = view.findViewById(R.id.optional_lesson_name)
        val optionLessonIntroduction: TextView = view.findViewById(R.id.optional_lesson_introduction)
        val checkOptionalButton: Button = view.findViewById(R.id.check_optional_lesson)
        val optionLesson = getItem(position)
        if(optionLesson != null) {
            tag.text = optionLesson.tag
            optionLessonName.text = optionLesson.name
            optionLessonIntroduction.text = optionLesson.introduction
            checkOptionalButton.setOnClickListener {
                context.startActivity(Intent(context, SelectLessonActivity::class.java).putExtra("key", optionLesson))
            }
        }
        return view
    }
}