package com.example.studyonline.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.Task

class TaskAdapter(
    context: Context,
    private val taskList: List<Task>

): RecyclerView.Adapter<RViewHolder>()
{
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
        val view = layoutInflater.inflate(R.layout.item_task, parent, false)
        return RViewHolder(view)
    }

    override fun onBindViewHolder(holder: RViewHolder, position: Int) {
        holder.taskCommitTime?.text = taskList[position].commitTime
        holder.taskOverview?.text   = taskList[position].taskOverview
    }

    override fun getItemCount(): Int = taskList.size
    companion object {
        private const val TYPE_TOP: Int = 0x0000
        private const val TYPE_NORMAL: Int = 0x0001
    }
}
class RViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
    var taskCommitTime: TextView? = itemView?.findViewById(R.id.task_commit_time)
    var taskOverview: TextView? = itemView?.findViewById(R.id.task_overview)
}