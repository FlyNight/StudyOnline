package com.example.studyonline.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.MainActivity
import java.util.*
import kotlin.collections.ArrayList

class DetailAdapter(
    context: Context,
    private val flag: Int,
    userId: Int,
    lessonId: Int
) : RecyclerView.Adapter<DetailViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val dataList = DetailViewHolder.getFromDatabase(flag, userId, lessonId)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = layoutInflater.inflate(R.layout.item_detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.serialNumber.text = dataList[position].serialNumber
        holder.reference.text = dataList[position].reference
        holder.actual.text = dataList[position].actual
        holder.done.text = dataList[position].done
        if (flag == 0) {
            if(position == 0)
                return
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val t1: Date = simpleDateFormat.parse(holder.reference.text.toString())
            val t2: Date = simpleDateFormat.parse(holder.actual.text.toString())
            if( (t1.time - t2.time) /1000  < 300) {
                holder.done.text = "是"
            } else {
                holder.done.text = "否"
            }
        } else {
            holder.done.visibility = View.GONE
        }
    }
}

class DetailViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val serialNumber: TextView = itemView!!.findViewById(R.id.serial_number)
    val reference: TextView = itemView!!.findViewById(R.id.reference)
    val actual: TextView = itemView!!.findViewById(R.id.actual)
    val done: TextView = itemView!!.findViewById(R.id.done)

    companion object {
        public fun getFromDatabase(selectType: Int, userId: Int, lessonId: Int): List<TmpBean> {
            val data: ArrayList<TmpBean> = arrayListOf(TmpBean("序号", "预计","实际", "完成？"))
            val t1 = Thread {
                val ps = MainActivity.cn.createStatement()
                var sql: String
                if (selectType == 0) {
                    sql = "SELECT\n" +
                            "\t* \n" +
                            "FROM\n" +
                            "\tattendances \n" +
                            "WHERE\n" +
                            "\tattendances.attendance_id IN (\n" +
                            "\tSELECT\n" +
                            "\t\tusers_outlines_attendances.attendance_id \n" +
                            "\tFROM\n" +
                            "\t\tusers_outlines_attendances \n" +
                            "\tWHERE\n" +
                            "\tusers_outlines_attendances.user_id = $userId \n" +
                            "\tAND users_outlines_attendances.outline_id IN ( SELECT lessons_outlines.outline_id FROM lessons_outlines WHERE lessons_outlines.lessons_id = $lessonId ))"
                } else if (selectType == 1) {
                    sql = "SELECT\n" +
                            "\t* \n" +
                            "FROM\n" +
                            "\tassessments \n" +
                            "WHERE\n" +
                            "\tassessments.assessment_id IN (\n" +
                            "\tSELECT\n" +
                            "\t\tusers_tasks_assessments.assessment_id \n" +
                            "\tFROM\n" +
                            "\t\tusers_tasks_assessments \n" +
                            "\tWHERE\n" +
                            "\tusers_tasks_assessments.user_id = $userId \n" +
                            "\tAND users_tasks_assessments.task_id IN ( SELECT lessons_tasks.task_id FROM lessons_tasks WHERE lessons_tasks.lesson_id = $lessonId ))"
                } else {
                    return@Thread
                }
                val resultSet = ps.executeQuery(sql)
                var i = 1
                while (resultSet.next()) {
                    data.add(
                        TmpBean(
                            i.toString(),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            ""
                        )
                    )
                    i++
                }
            }
            t1.start()
            t1.join()
            return data
        }
    }
}

class TmpBean(
    val serialNumber: String,
    val reference: String,
    val actual: String,
    val done: String
)