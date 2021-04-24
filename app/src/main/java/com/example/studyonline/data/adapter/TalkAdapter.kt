package com.example.studyonline.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.TalkBean


class TalkAdapter(
    context: Context,
    private val talkList: List<TalkBean>
):RecyclerView.Adapter<VH>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = layoutInflater.inflate(R.layout.item_talk, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.time?.text = talkList[position].currentTime
        holder.name?.text = talkList[position].userName
        holder.message?.text = talkList[position].message
    }

    override fun getItemCount(): Int = talkList.size
}

class VH(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
    var name: TextView? = itemView?.findViewById(R.id.name)
    var time: TextView? = itemView?.findViewById(R.id.time)
    var message: TextView? = itemView?.findViewById(R.id.message)
}