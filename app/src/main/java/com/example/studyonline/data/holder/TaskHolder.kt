package com.example.studyonline.data.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.MainActivity.Companion.cn
import com.example.studyonline.data.bean.Task

class TaskHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val commitTime : TextView = itemView!!.findViewById(R.id.commit_time)
    val taskDetail : TextView = itemView!!.findViewById(R.id.task_detail)
    companion object {
        fun getDataFromDatabase(context: Context, lessonId: Int): ArrayList<Task> {
            val data: ArrayList<Task> = ArrayList()
            val thread = Thread {
                val taskIdList: ArrayList<Int> = ArrayList()
                val ps = cn.createStatement()
                var resultSet = ps.executeQuery("select * from lessons_tasks where lesson_id = $lessonId")
                while (resultSet.next()) {
                    taskIdList.add(resultSet.getInt("task_id"))
                }
                for (element in taskIdList) {
                    resultSet = ps.executeQuery("select * from tasks where task_id = $element")
                    resultSet.next()
                    data.add(
                        Task(
                            resultSet.getString("commit_time"),
                            resultSet.getString("task_name"),
                            resultSet.getString("task_overview"),
                            context.resources.getIdentifier(resultSet.getString("task_color"), "color", "com.example.studyonline"),
                            context.resources.getIdentifier(resultSet.getString("task_res"), "drawable", "com.example.studyonline")
                        )
                    )
                }
                ps.close()
            }
            thread.start()
            thread.join()
            return data
        }
    }
}