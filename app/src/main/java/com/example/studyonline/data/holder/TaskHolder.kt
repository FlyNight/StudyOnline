package com.example.studyonline.data.holder

import android.content.Context
import com.example.studyonline.MainActivity.Companion.cn
import com.example.studyonline.data.bean.Task

class TaskHolder {
    companion object {
        fun getDataFromDatabase(context: Context, lessonId: Int): ArrayList<Task> {
            val data: ArrayList<Task> = ArrayList()
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
            return data
        }
    }
}