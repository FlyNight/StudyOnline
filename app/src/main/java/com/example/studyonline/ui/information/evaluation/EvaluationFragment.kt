package com.example.studyonline.ui.information.evaluation

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.LessonInformationActivity
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.data.adapter.DetailAdapter
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

class EvaluationFragment(lessonId: Int) : Fragment() {

    companion object {
        fun newInstance(lessonId: Int) = EvaluationFragment(lessonId)
    }

    private lateinit var viewModel: EvaluationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_evaluation, container, false)
        val radarChart: RadarChart = root.findViewById(R.id.radar_chart)
        radarChart.webColorInner = Color.BLACK
        radarChart.webColor = Color.BLACK
        radarChart.webAlpha = 50
        radarChart.description.text = "${MainActivity.userName} 的个人评价"
        radarChart.description.textSize = 15f
        val xAxis: XAxis = radarChart.xAxis
        xAxis.setLabelCount(3, true)
        xAxis.axisMaximum = 2f
        xAxis.axisMinimum = 0f
        xAxis.textSize = 12f
        xAxis.textColor = R.color.blossom
        xAxis.valueFormatter = object : ValueFormatter() {
            val strings = listOf("考勤", "作业","教师评价")
            override fun getFormattedValue(value: Float): String {
                return strings[value.toInt()]
            }
        }

        val list: ArrayList<RadarEntry> =  ArrayList();
        val t1 = Thread {
            val ps= MainActivity.cn.createStatement()
            var resultSet = ps.executeQuery("select * from evaluations_users_lessons where " +
                    "user_id = ${MainActivity.id} and lesson_id = ${LessonInformationActivity.lessonId}")
            resultSet.next()
            val evaluationId = resultSet.getInt("evaluation_id")
            resultSet = ps.executeQuery("select * from evaluations where evaluation_id = $evaluationId")
            resultSet.next()
            list.add(RadarEntry(resultSet.getFloat("attendance")))
            list.add(RadarEntry(resultSet.getFloat("assessment")))
            list.add(RadarEntry(resultSet.getFloat("appraise")))
            ps.close()
        }
        t1.start()
        t1.join()
        val yAxis: YAxis = radarChart.yAxis
        yAxis.setLabelCount(3, true);
        yAxis.axisMinimum = 0f;
        yAxis.setDrawTopYLabelEntry(false);
        yAxis.textSize = 15f;
        yAxis.textColor = Color.RED;

        radarChart.setDrawWeb(true);
        radarChart.description.isEnabled = true;
        radarChart.legend.isEnabled = false;

        radarChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            val dataContainer: RecyclerView = RecyclerView(context!!)
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val layoutManager = LinearLayoutManager(context)
                dataContainer.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,800)
                dataContainer.layoutManager = layoutManager
                dataContainer.adapter = DetailAdapter(context!!, h!!.x.toInt(),
                    MainActivity.id, LessonInformationActivity.lessonId
                )
                BottomDialog.Builder(context!!)
                    .setCustomView(dataContainer)
                    .show()
            }

            override fun onNothingSelected() {
                radarChart.isSelected = false
            }

        })

        val set =  RadarDataSet(list, "evaluation ${MainActivity.id} ${LessonInformationActivity.lessonId}")

        set.setDrawValues(true)
        set.valueTextSize = 15f
        set.fillColor = Color.BLUE
        set.fillAlpha = 40
        set.setDrawFilled(true)
        set.isDrawHighlightCircleEnabled = true
        set.highlightCircleFillColor = Color.RED
        set.highlightCircleStrokeAlpha = 40
        set.highlightCircleInnerRadius = 20f
        set.highlightCircleOuterRadius = 10f

        val data = RadarData(set)
        radarChart.data = data
        radarChart.invalidate()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EvaluationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}