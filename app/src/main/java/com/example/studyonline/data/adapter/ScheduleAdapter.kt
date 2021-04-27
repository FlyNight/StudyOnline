package com.example.studyonline.data.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.Outline
import com.example.studyonline.data.holder.OutlineHolder

class ScheduleAdapter(context: Context, private val outlineList: List<Outline>) :
    RecyclerView.Adapter<OutlineHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutlineHolder {
        val view = layoutInflater.inflate(R.layout.item_schedule, parent, false)
        return OutlineHolder(view)
    }


    override fun onBindViewHolder(holder: OutlineHolder, position: Int) {
        holder.outlineDetail.text = outlineList[position].name
    }


    override fun getItemCount(): Int = outlineList.size

}