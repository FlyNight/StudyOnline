package com.example.studyonline.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.holder.ChattoHolder

class ChattoAdapter(context: Context, chattoId: Int, userId: Int): RecyclerView.Adapter<ChattoHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private val chattoList = ChattoHolder.getChattoList(chattoId, userId)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattoHolder {
        val view = if (viewType == 0)
            layoutInflater.inflate(R.layout.item_chatto_right, parent, false)
        else
            layoutInflater.inflate(R.layout.item_chatto_left, parent, false)
        return ChattoHolder(view)
    }

    override fun onBindViewHolder(holder: ChattoHolder, position: Int) {
        holder.msg.text = chattoList[position].message
        holder.sendTime.text = chattoList[position].sendTime
    }

    override fun getItemCount(): Int = chattoList.size

    override fun getItemViewType(position: Int): Int {
        return chattoList[position].type
    }
}