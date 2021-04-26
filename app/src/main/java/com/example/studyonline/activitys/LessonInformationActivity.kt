package com.example.studyonline.activitys

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import com.example.studyonline.R
import com.example.studyonline.data.bean.LessonBean
import com.example.studyonline.ui.information.SectionsPagerAdapter
import java.io.Serializable
import kotlin.properties.Delegates

class LessonInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_information)
        val se: Serializable? = intent.getSerializableExtra("lesson")
        if(se is LessonBean) {
            val lesson: LessonBean = se
            lessonId = lesson.id
        }

        val tabs: TabLayout = findViewById(R.id.tabs)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        tabs.setupWithViewPager(viewPager, true)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, lessonId)
        viewPager.adapter = sectionsPagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
        })



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    companion object {
        var lessonId by Delegates.notNull<Int>()
        val titles = arrayOf("章节","作业","评价")
    }
}