package com.example.studyonline.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.Task
import com.example.studyonline.data.holder.TaskHolder

class TaskAdapter(
    context: Context,
    private val taskList: List<Task>

): RecyclerView.Adapter<TaskHolder>()
{
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = layoutInflater.inflate(R.layout.item_task, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.commitTime.text = taskList[position].commitTime.substring(11,19)
        holder.taskDetail.text   = taskList[position].taskOverview
    }

    override fun getItemCount(): Int = taskList.size
    companion object {
        private const val TYPE_TOP: Int = 0x0000
        private const val TYPE_NORMAL: Int = 0x0001
    }
}