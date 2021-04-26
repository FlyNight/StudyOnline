package com.example.studyonline.ui.information.schedule

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

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    class ScheduleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvName = itemView!!.findViewById<TextView>(R.id.tv_name)!!
        val tvDetail = itemView!!.findViewById<TextView>(R.id.tv_detail)!!
    }

    class ScheduleItem()
}