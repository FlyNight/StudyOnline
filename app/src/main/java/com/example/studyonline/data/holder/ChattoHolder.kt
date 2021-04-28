package com.example.studyonline.data.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.bean.ChatBean
import com.example.studyonline.data.bean.ChattoBean
import java.text.SimpleDateFormat

class ChattoHolder(itemVIew: View?): RecyclerView.ViewHolder(itemVIew!!) {
    val sendTime:TextView = itemVIew!!.findViewById(R.id.send_time)
    val msg: TextView = itemVIew!!.findViewById(R.id.msg)

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getChattoList(chattoId: Int, userId: Int): List<ChattoBean> {
            val chattoList = ArrayList<ChattoBean>()
            var tmpList: List<ChatBean>? = null
            val mergeChatList = ChatHolder.getMergeList(userId)
            for (i in mergeChatList) {
                if (i[0].sendId == userId && i[0].receiveId == chattoId) {
                    tmpList = i
                    break
                } else if (i[0].receiveId == userId && i[0].sendId == chattoId) {
                    tmpList = i
                    break
                }
            }
            if (tmpList != null) {
                for (i in tmpList) {
                    var flag = -1
                    flag = if (i.sendId == userId)
                        0
                    else
                        1
                    chattoList.add(
                        ChattoBean(
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(i.sendTime),
                            i.message,
                            flag
                        )
                    )
                }
            }
            return chattoList
        }
    }
}