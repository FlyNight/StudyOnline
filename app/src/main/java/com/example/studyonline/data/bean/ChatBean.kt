package com.example.studyonline.data.bean

import java.sql.Timestamp

class ChatBean (
    val id: Int,
    val sendId: Int,
    val receiveId: Int,
    val sendTime: Timestamp,
    val message: String,
    val isRead: Boolean
    )
class ChatShowBean (
    val chatTo: String,
    val lastMessage: String,
    val lastTime: String
    )
class ChattoBean (
    val sendTime: String,
    val message: String,
    val type: Int
    )