package com.example.studyonline.data.holder

import android.content.Context
import android.util.Log
import com.example.studyonline.MainActivity
import com.example.studyonline.data.bean.Outline
import com.example.studyonline.data.bean.Task

class OutlineHolder {
    companion object {
        fun getDataFromDatabase(context: Context, lessonId: Int): ArrayList<Outline> {
            val data: ArrayList<Outline> = ArrayList()
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
            return data
        }
    }
}