package com.example.studyonline.ui.information

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.LessonInformationActivity.Companion.lessonId
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.data.adapter.ScheduleAdapter
import com.example.studyonline.data.adapter.TaskAdapter
import com.example.studyonline.data.StepSTL
import com.example.studyonline.data.adapter.DetailAdapter
import com.example.studyonline.data.bean.LessonBean
import com.github.javiersantos.bottomdialogs.BottomDialog
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration
import com.orient.me.widget.rv.itemdocration.timeline.TimeLine
import com.orient.me.widget.rv.layoutmanager.DoubleSideLayoutManager


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private val colors = arrayOf(Color.RED, Color.BLACK, Color.GREEN, Color.BLUE, Color.GRAY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_lesson_information, container, false)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_LESSON_ID = "lesson_id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, lessonId: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {

                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putInt(ARG_LESSON_ID, lessonId)
                }
            }
        }
    }
}