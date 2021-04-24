package com.example.studyonline.ui.information

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.adapter.LessonScheduleAdapter
import com.example.studyonline.data.adapter.TaskAdapter
import com.example.studyonline.data.StepSTL
import com.example.studyonline.data.bean.LessonBean
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration
import com.orient.me.widget.rv.itemdocration.timeline.TimeLine
import com.orient.me.widget.rv.layoutmanager.DoubleSideLayoutManager

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    val colors = arrayOf(Color.RED, Color.BLACK, Color.GREEN, Color.BLUE, Color.GRAY)

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
        val root = inflater.inflate(R.layout.fragment_lesson_information, container, false)
        val lessonInformationSchedule: View = root.findViewById(R.id.lesson_schedule)
        val lessonInformationTask: View = root.findViewById(R.id.lesson_task)
        val lessonInformationDiscuss: View = root.findViewById(R.id.lesson_discuss)
        pageViewModel.values.observe(viewLifecycleOwner, Observer {
            when(it) {
                1 -> {
                    lessonInformationSchedule.visibility = View.VISIBLE
                    lessonInformationTask.visibility = View.GONE
                    lessonInformationDiscuss.visibility = View.GONE
                }
                2 -> {
                    lessonInformationSchedule.visibility = View.GONE
                    lessonInformationTask.visibility = View.VISIBLE
                    lessonInformationDiscuss.visibility = View.GONE
                }
                3 -> {
                    lessonInformationSchedule.visibility = View.GONE
                    lessonInformationTask.visibility = View.GONE
                    lessonInformationDiscuss.visibility = View.VISIBLE
                }
            }
        })
        initSchedulePage(root)
        initTaskPage(root)
        initRecordPage(root)
        return root
    }

    private fun initSchedulePage(root: View) {
        val lessonScheduleList: ListView = root.findViewById(R.id.lesson_schedule_list)
        var index = 0
        for (element in LessonBean.testData1) {
            if (element.id == arguments?.getInt(ARG_LESSON_ID) as Int) {
                index = element.id
            }
        }
        val adapter =
            activity?.let { LessonScheduleAdapter(it, R.layout.item_lesson_schedule, LessonBean.testData1[index - 1].outline) }
        lessonScheduleList.adapter = adapter
    }

    private fun initTaskPage(root: View) {
        var index = 0
        for (element in LessonBean.testData1) {
            if (element.id == arguments?.getInt(ARG_LESSON_ID) as Int) {
                index = element.id
            }
        }
        val taskList: RecyclerView = root.findViewById(R.id.task_list)
        taskList.layoutManager = DoubleSideLayoutManager()
        val adapter = context?.let { TaskAdapter(it, LessonBean.testData1[index - 1].task) }
        val singleDecoration = TimeLine.Builder(context,LessonBean.testData1[index - 1].task)
            .setTitle(Color.parseColor("#ffffff"),20)
            .setTitleStyle(SingleTimeLineDecoration.FLAG_TITLE_TYPE_LEFT,100)
            .setLine(SingleTimeLineDecoration.FLAG_LINE_BEGIN_TO_END,20,Color.parseColor("#8d9ca9"),2)
            .setDot(SingleTimeLineDecoration.FLAG_DOT_DRAW)
            .build(StepSTL::class.java)
        taskList.adapter = adapter
        taskList.addItemDecoration(singleDecoration)
    }

    private fun initRecordPage(root: View) {
        val radarChart: RadarChart = root.findViewById(R.id.radar_chart)
        radarChart.webColorInner = Color.BLACK
        radarChart.webColor = Color.BLACK
        radarChart.webAlpha = 50

        val xAxis: XAxis = radarChart.xAxis
        xAxis.setLabelCount(4, true)
        xAxis.axisMaximum = 4f
        xAxis.axisMinimum = 0f
        xAxis.textSize = 20f
        xAxis.textColor = colors[1]

        val yAxis: YAxis = radarChart.yAxis
        yAxis.setLabelCount(5, true);
        yAxis.axisMinimum = 0f;
        yAxis.setDrawTopYLabelEntry(false);
        yAxis.textSize = 15f;
        yAxis.textColor = Color.RED;

        radarChart.setDrawWeb(true);
        radarChart.description.isEnabled = false;
        radarChart.legend.isEnabled = false;

        val list: ArrayList<RadarEntry> =  ArrayList();
        for (i in 0..4) {
            list.add(RadarEntry( ((Math.random() * 100).toFloat())))
        }
        val set: RadarDataSet =  RadarDataSet(list, "Petterp")

        //禁用标签
        set.setDrawValues(false);
        //设置填充颜色
        set.fillColor = Color.BLUE;
        //设置填充透明度
        set.fillAlpha = 40;
        //设置启用填充
        set.setDrawFilled(true);
        //设置点击之后标签是否显示圆形外围
        set.isDrawHighlightCircleEnabled = true;
        //设置点击之后标签圆形外围的颜色
        set.highlightCircleFillColor = Color.RED;
        //设置点击之后标签圆形外围的透明度
        set.highlightCircleStrokeAlpha = 40;
        //设置点击之后标签圆形外围的半径
        set.highlightCircleInnerRadius = 20f;
        //设置点击之后标签圆形外围内圆的半径
        set.highlightCircleOuterRadius = 10f;


        val data: RadarData = RadarData(set);
        radarChart.data = data;
        radarChart.invalidate();
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