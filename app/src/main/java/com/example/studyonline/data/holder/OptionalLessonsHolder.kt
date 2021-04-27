package com.example.studyonline.data.holder

import android.content.Context
import android.util.Log
import com.example.studyonline.activitys.MainActivity.Companion.cn
import com.example.studyonline.data.bean.LessonBean

class OptionalLessonsHolder {
    companion object {
        fun getDataFromDatabase(context: Context, userAccount: String?): ArrayList<LessonBean> {
            val data: ArrayList<LessonBean> = ArrayList()
            val ps = cn.createStatement()
            if (userAccount != null) {
                val lessonIdList: ArrayList<Int> = ArrayList()
                var resultSet = ps.executeQuery("select * from users where user_account = $userAccount")
                resultSet.next()
                val userId = resultSet.getInt("user_id")
                resultSet = ps.executeQuery("select * from users_lessons where user_id = $userId")
                while (resultSet.next()) {
                    lessonIdList.add(resultSet.getInt("lesson_id"))
                }
                for (element in lessonIdList) {
                    resultSet = ps.executeQuery("select * from lessons where Lesson_id = $element")
                    resultSet.next()
                    val lessonId = resultSet.getInt("lesson_id")
                    val ps1 = cn.createStatement()
                    val resultSet1 = ps1.executeQuery(
                        "select *" +
                                " from users_lessons where lesson_id = $lessonId"
                    )
                    resultSet1.next()
                    val userId = resultSet1.getInt("user_id")
                    ps1.close()
                    val ps2 = cn.createStatement()
                    val resultSet2 = ps2.executeQuery(
                        "select *" +
                                " from users where user_id = $userId"
                    )
                    resultSet2.next()
                    val userName = resultSet2.getString("user_name")
                    ps2.close()
                    val lessonName = resultSet.getString("lesson_name")
                    val introduction = resultSet.getString("introduction")
                    val overview = resultSet.getString("overview")
                    val time = resultSet.getTime("time")
                    val weekday = resultSet.getInt("weekday")
                    val startWeek = resultSet.getInt("start_week")
                    val duration = resultSet.getInt("duration")
                    val tag = resultSet.getString("tag")
                    data.add(
                        LessonBean(
                            lessonId,
                            lessonName,
                            userName,
                            introduction,
                            overview,
                            "${time}_$weekday",
                            startWeek,
                            duration,
                            tag,
                            OutlineHolder.getDataFromDatabase(lessonId),
                            TaskHolder.getDataFromDatabase(context,lessonId)
                        )
                    )
                }
                ps.close()
                Log.d("mysql", data.toString())
                return data
            }
            val resultSet = ps.executeQuery("select * from lessons")
            while (resultSet.next()) {
                val lessonId = resultSet.getInt("lesson_id")
                val ps1 = cn.createStatement()
                val resultSet1 = ps1.executeQuery(
                    "select *" +
                            " from users_lessons where lesson_id = $lessonId"
                )
                resultSet1.next()
                val userId = resultSet1.getInt("user_id")
                ps1.close()
                val ps2 = cn.createStatement()
                val resultSet2 = ps2.executeQuery(
                    "select *" +
                            " from users where user_id = $userId"
                )
                resultSet2.next()
                val userName = resultSet2.getString("user_name")
                ps2.close()
                val lessonName = resultSet.getString("lesson_name")
                val introduction = resultSet.getString("introduction")
                val overview = resultSet.getString("overview")
                val time = resultSet.getTime("time")
                val weekday = resultSet.getInt("weekday")
                val startWeek = resultSet.getInt("start_week")
                val duration = resultSet.getInt("duration")
                val tag = resultSet.getString("tag")
                data.add(
                    LessonBean(
                        lessonId,
                        lessonName,
                        userName,
                        introduction,
                        overview,
                        "${time}_$weekday",
                        startWeek,
                        duration,
                        tag,
                        OutlineHolder.getDataFromDatabase(lessonId),
                        TaskHolder.getDataFromDatabase(context,lessonId)
                    )
                )
            }
            ps.close()
            Log.d("mysql", data.toString())
            return data
        }
    }
}