package com.example.studyonline.activitys

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.studyonline.R
import com.example.studyonline.data.bean.LessonBean
import com.example.studyonline.ui.information.SectionsPagerAdapter
import java.io.Serializable

class LessonInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_information)
        val se: Serializable? = intent.getSerializableExtra("lesson")
        var lessonId = 0
        if(se is LessonBean) {
            val lesson: LessonBean = se
            lessonId = lesson.id
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, lessonId)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}