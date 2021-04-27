package com.example.studyonline.ui.information.task

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
import com.example.studyonline.data.NoteInfoSTL
import com.example.studyonline.data.StepSTL
import com.example.studyonline.data.adapter.TaskAdapter
import com.example.studyonline.data.holder.TaskHolder
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration
import com.orient.me.widget.rv.itemdocration.timeline.TimeLine
import com.orient.me.widget.rv.layoutmanager.DoubleSideLayoutManager

class TaskFragment(val lessonId: Int) : Fragment() {

    companion object {
        fun newInstance(lessonId: Int) = TaskFragment(lessonId)
    }

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        val taskList = view.findViewById<RecyclerView>(R.id.task_list)
        taskList.layoutManager = LinearLayoutManager(context)
        val taskData = TaskHolder.getDataFromDatabase(requireContext(), lessonId)
        taskList.adapter = context?.let { TaskAdapter(it, taskData) }
        val decoration = TimeLine.Builder(context, taskData)
            .setTitle(Color.parseColor("#ffffff"), 20)
            .setTitleStyle(SingleTimeLineDecoration.FLAG_TITLE_TYPE_LEFT, 80)
            .setLine(SingleTimeLineDecoration.FLAG_LINE_CONSISTENT, 0, Color.parseColor("#8d9ca9"))
            .setDot(SingleTimeLineDecoration.FLAG_DOT_DRAW)
            .setSameTitleHide()
            .build(NoteInfoSTL::class.java)
        taskList.addItemDecoration(decoration)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}