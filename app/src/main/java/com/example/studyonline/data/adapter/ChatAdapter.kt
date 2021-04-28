package com.example.studyonline.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.ChatShowBean
import com.example.studyonline.data.holder.ChatHolder
import java.util.*

class ChatAdapter(val context: Context, val userId: Int): RecyclerView.Adapter<ChatHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var chatList: List<ChatShowBean> = ChatHolder.getFromDatabase(userId)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view = layoutInflater.inflate(R.layout.item_chat, parent, false)
        return ChatHolder(context,parent, view, userId)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.chatTo.text = chatList[position].chatTo
        holder.lastMessage.text = chatList[position].lastMessage
        holder.lastTime.text = chatList[position].lastTime
    }

    override fun getItemCount(): Int = chatList.size


}