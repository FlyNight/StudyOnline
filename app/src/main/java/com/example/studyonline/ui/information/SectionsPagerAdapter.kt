package com.example.studyonline.ui.information

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.studyonline.R
import com.example.studyonline.ui.information.evaluation.EvaluationFragment
import com.example.studyonline.ui.information.schedule.ScheduleFragment
import com.example.studyonline.ui.information.task.TaskFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, private val lessonId: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ScheduleFragment.newInstance(lessonId)
            }
            1 -> {
                TaskFragment.newInstance(lessonId)
            }
            2 -> {
                EvaluationFragment.newInstance(lessonId)
            }
            else -> {
                ScheduleFragment.newInstance(lessonId)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}