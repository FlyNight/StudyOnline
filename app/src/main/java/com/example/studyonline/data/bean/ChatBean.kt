package com.example.studyonline.data.bean

import java.sql.Timestamp

class ChatBean (
    val id: Int,
    val sendId: Int,
    val receive: Int,
    val sendTime: Timestamp,
    val message: String,
    val idRead: Boolean
    )