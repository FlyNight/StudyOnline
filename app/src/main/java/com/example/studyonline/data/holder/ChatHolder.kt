package com.example.studyonline.data.holder

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.activitys.MainActivity
import com.example.studyonline.data.adapter.ChattoAdapter
import com.example.studyonline.data.bean.ChatBean
import com.example.studyonline.data.bean.ChatShowBean
import com.github.javiersantos.bottomdialogs.BottomDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatHolder(val context: Context,val parent: ViewGroup,itemView: View?, userId: Int) : RecyclerView.ViewHolder(itemView!!) {
    val chatTo: TextView = itemView!!.findViewById(R.id.chat_to)
    val lastMessage: TextView = itemView!!.findViewById(R.id.last_message)
    val lastTime: TextView = itemView!!.findViewById(R.id.last_time)
    init {
        itemView!!.setOnClickListener {
            val chattoId = getIdByName(chatTo.text.toString())
            val dialog = BottomDialog.Builder(context)
                .setTitle(chatTo.text)
                .setCustomView(getChatToView(chattoId,userId))
                .build()
            dialog.show()
        }
    }
    var chattoList: RecyclerView? = null
    var messageEdit: EditText? = null
    private fun getChatToView(chattoId: Int, userId: Int): View {
        val root = LayoutInflater.from(context).inflate(R.layout.dialog_chatto, parent, false)
         chattoList = root.findViewById(R.id.chatto_list)
        chattoList!!.layoutManager = LinearLayoutManager(context)
        chattoList!!.adapter = ChattoAdapter(context, chattoId, userId)
        Timer().scheduleAtFixedRate(
            object: TimerTask() {
                override fun run() {
                    val message = Message()
                    message.what = 1
                    message.arg1 = chattoId
                    handler.sendMessage(message)
                }
            },
            0, 1000)
        chattoList!!.scrollToPosition(chattoList!!.adapter!!.itemCount - 1)
        chattoList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < -10) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(root.windowToken, 0)
                }
            }
        })

        messageEdit = root.findViewById(R.id.message_edit)
        val messageSend: Button = root.findViewById(R.id.message_send)
        messageSend.setOnClickListener {
            if (messageEdit!!.text != null) {
                val message = Message()
                message.what = 2
                message.arg1 = chattoId
                handler.sendMessage(message)
            }
        }
        return root
    }

    private val handler = Handler() {
        when (it.what) {
            1 -> {
                chattoList!!.adapter = ChattoAdapter(context, it.arg1, userId)
            }
            2 -> {
                sendMessage(userId, it.arg1, messageEdit!!.text.toString())
            }
        }
        false
    }

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getFromDatabase(userId: Int): List<ChatShowBean> {
            val chatShowList = ArrayList<ChatShowBean>()
            val thread = Thread {
                val mergeChatList = getMergeList(userId)
                val ps = MainActivity.cn.createStatement()
                for (i in mergeChatList) {
                    val arg1 = if (i[0].sendId == userId)
                        i[0].receiveId
                    else
                        i[0].sendId
                    val resultSet = ps.executeQuery("SELECT * FROM users WHERE user_id = $arg1")
                    resultSet.next()
                    chatShowList.add(
                        ChatShowBean(
                            chatTo = resultSet.getString("user_name"),
                            lastTime = SimpleDateFormat("HH:mm").format(i[i.size - 1].sendTime),
                            lastMessage = i[i.size - 1].message
                        )
                    )
                }
            }
            thread.start()
            thread.join()
            return chatShowList
        }

        fun getMergeList(userId: Int): List<List<ChatBean>> {
            val tmpChatList = ArrayList<List<ChatBean>>()
            val thread = Thread{
                val chatList = ArrayList<ChatBean>()
                val ps = MainActivity.cn.createStatement()
                var resultSet = ps.executeQuery("SELECT * FROM chats WHERE send_id = $userId")
                while (resultSet.next()) {
                    chatList.add(
                        ChatBean(
                            id = resultSet.getInt("id"),
                            sendId = resultSet.getInt("send_id"),
                            receiveId = resultSet.getInt("receive_id"),
                            sendTime = resultSet.getTimestamp("send_time"),
                            message = resultSet.getString("message"),
                            isRead = resultSet.getBoolean("is_read")
                        )
                    )
                }
                resultSet = ps.executeQuery("SELECT * FROM chats WHERE receive_id = $userId")
                while (resultSet.next()) {
                    chatList.add(
                        ChatBean(
                            id = resultSet.getInt("id"),
                            sendId = resultSet.getInt("send_id"),
                            receiveId = resultSet.getInt("receive_id"),
                            sendTime = resultSet.getTimestamp("send_time"),
                            message = resultSet.getString("message"),
                            isRead = resultSet.getBoolean("is_read")
                        )
                    )
                }
                for (i in chatList) {
                    var flag = false
                    for (j in tmpChatList) {
                        val arg1 = if (i.sendId == userId)
                            i.receiveId
                        else
                            i.sendId
                        val arg2 = if (j[0].sendId == userId)
                            j[0].receiveId
                        else
                            j[0].sendId
                        if (arg1 == arg2)
                            flag = true
                    }
                    if (flag)
                        continue
                    val tmpList = ArrayList<ChatBean>()
                    for (j in chatList) {
                        val arg1 = if (i.sendId == userId)
                            i.receiveId
                        else
                            i.sendId
                        val arg2 = if (j.sendId == userId)
                            j.receiveId
                        else
                            j.sendId
                        if (arg1 == arg2)
                            tmpList.add(j)
                    }
                    tmpList.sortBy {
                        it.sendTime.time
                    }
                    tmpChatList.add(tmpList)
                }
                ps.close()
            }
            thread.start()
            thread.join()
            return tmpChatList
        }

        private fun getIdByName(name: String): Int {
            var id = -1
            val thread = Thread{
                val ps = MainActivity.cn.createStatement()
                val resultSet = ps.executeQuery("SELECT * FROM users WHERE user_name = \'$name\'")
                resultSet.next()
                id = resultSet.getInt("user_id")
                ps.close()
            }
            thread.start()
            thread.join()
            return id
        }

        @SuppressLint("SimpleDateFormat")
        private fun sendMessage(sendId: Int, receiveId: Int, message: String) {
            val thread = Thread {
                val isRead = false
                val time: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(System.currentTimeMillis()))
                val pstm = MainActivity.cn.prepareStatement("INSERT INTO chats(send_id, receive_id, send_time, message, is_read) VALUES (" +
                        "?, ?, ?, ?, ?)")
                pstm.setInt(1, sendId)
                pstm.setInt(2, receiveId)
                pstm.setString(3, time)
                pstm.setString(4, message)
                pstm.setBoolean(5, isRead)
                pstm.executeUpdate()
                pstm.close()
            }
            thread.start()
            thread.join()
        }

    }
}