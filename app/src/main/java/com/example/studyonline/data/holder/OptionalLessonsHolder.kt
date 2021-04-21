package com.example.studyonline.data.holder

import com.example.studyonline.MainActivity
import com.example.studyonline.data.bean.LessonBean

class OptionalLessonsHolder {
    val data: List<LessonBean> = ArrayList<LessonBean>()
    fun getDataFromDatabase() {
        var ps = MainActivity.cn.createStatement()
        val resultSet = ps.executeQuery("select * from lessons")
        while (resultSet.next()) {

        }
    }
}