package com.example.studyonline.data.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.data.bean.Outline

class OutlineHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val outlineDetail : TextView = itemView!!.findViewById(R.id.schedule_detail)

    companion object {
        fun getDataFromDatabase(lessonId: Int): ArrayList<Outline> {
            val data: ArrayList<Outline> = ArrayList()
            val thread = Thread {
                val outlineIdList: ArrayList<Int> = ArrayList()
                val ps = MainActivity.cn.createStatement()
                var resultSet = ps.executeQuery("select * from lessons_outlines where lessons_id = $lessonId")
                while (resultSet.next()) {
                    outlineIdList.add(resultSet.getInt("outline_id"))
                }
                for (element in outlineIdList) {
                    resultSet = ps.executeQuery("select * from outlines where outline_id = $element")
                    resultSet.next()
                    data.add(
                        Outline(
                            resultSet.getBoolean("done"),
                            resultSet.getString("outline_name")
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