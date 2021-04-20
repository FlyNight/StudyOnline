package com.example.studyonline.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.studyonline.R
import com.example.studyonline.data.bean.LessonBean

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        initGraph(root)
        return root
    }

    private fun initGraph(root: View) {
        for (element in LessonBean.testData1) {
            var time: Int
            when(element.time.substring(0,2).toInt() / 2) {
                4 -> {
                    time = 1
                }
                5 -> {
                    time = 2
                }
                7 -> {
                    time = 3
                }
                8 -> {
                    time = 4
                }
                10 -> {
                    time = 5
                }
                else -> {
                    time = 0
                }
            }
            val day = (element.time.last().toInt() - 47) % 7
            if(time in 1..5 && day in 1..7) {
                val linearLayoutString: String = "lesson_$time" + "_day_$day" + "_l"
                val linearLayoutId: Int? = context?.resources?.getIdentifier(
                    linearLayoutString,
                    "id",
                    "com.example.studyonline")
                val linearLayout: LinearLayout? = linearLayoutId?.let { root.findViewById(it) }
                if (linearLayout != null) {
                    linearLayout.visibility = View.VISIBLE
                    linearLayout.orientation = VERTICAL
                    val lessonName = TextView(context)
                    lessonName.text = element.name
                    lessonName.textSize = 20f
                    lessonName.gravity = Gravity.CENTER
                    linearLayout.addView(
                        lessonName,
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            0,
                            2f))
                    val lessonTeacher = TextView(context)
                    lessonTeacher.text = element.teacher
                    lessonTeacher.textSize = 15f
                    lessonTeacher.gravity = Gravity.CENTER
                    linearLayout.addView(
                        lessonTeacher,
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            0,
                            1f))
                    linearLayout.setOnClickListener {
                        Toast.makeText(context, lessonName.text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                println("Wrong time !!!$time  $day")
            }
        }
    }
}