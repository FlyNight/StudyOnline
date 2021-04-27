package com.example.studyonline.ui.information.schedule

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.SocialMediaSTL
import com.example.studyonline.data.StepSTL
import com.example.studyonline.data.adapter.ScheduleAdapter
import com.example.studyonline.data.bean.Outline
import com.example.studyonline.data.holder.OutlineHolder
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration
import com.orient.me.widget.rv.itemdocration.timeline.TimeLine

class ScheduleFragment(val lessonId: Int) : Fragment() {

    companion object {
        fun newInstance(lessonId: Int) = ScheduleFragment(lessonId)
    }

    private lateinit var viewModel: ScheduleViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        val scheduleList = view.findViewById<RecyclerView>(R.id.schedule_list)
        scheduleList.layoutManager = LinearLayoutManager(context)
        val outlineList: List<Outline> = OutlineHolder.getDataFromDatabase(lessonId)
        scheduleList.adapter = context?.let { ScheduleAdapter(it, outlineList) }
        val decoration = TimeLine.Builder(context, outlineList)
            .setTitle(Color.parseColor("#000000"),22)
            .setTitleStyle(SingleTimeLineDecoration.FLAG_TITLE_TYPE_TOP,52)
            .setLine(SingleTimeLineDecoration.FLAG_LINE_DIVIDE,80, Color.parseColor("#757575"),1)
            .setDot(SingleTimeLineDecoration.FLAG_DOT_DRAW)
            .setSameTitleHide()
            .build(SocialMediaSTL::class.java)
        scheduleList.addItemDecoration(decoration)
        return view
    }


}