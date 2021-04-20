package com.example.studyonline.data.bean

import kotlin.collections.ArrayList

class UserBean(
    val id: Int,
    val userId: String,
    val userName: String,
    val userPassword: String,
    val userIdentity: Identity,
    val lessons: ArrayList<Int>
) {
    companion object {
        val testData5: List<UserBean>
            get() {
                val list: MutableList<UserBean> = ArrayList()
                list.add(
                    UserBean(
                        1,
                        "10001",
                        "zhao",
                        "123456",
                        Identity.TEACHER,
                        ArrayList(listOf(1))
                    )
                )
                list.add(
                    UserBean(
                        2,
                        "10002",
                        "qian",
                        "123456",
                        Identity.TEACHER,
                        ArrayList(listOf(2))
                    )
                )
                list.add(
                    UserBean(
                        3,
                        "sun",
                        "10003",
                        "123456",
                        Identity.TEACHER,
                        ArrayList(listOf(3))
                    )
                )
                list.add(
                    UserBean(
                        4,
                        "10004",
                        "li",
                        "123456",
                        Identity.TEACHER,
                        ArrayList(listOf(4))
                    )
                )
                list.add(
                    UserBean(
                        5,
                        "10005",
                        "zhou",
                        "123456",
                        Identity.TEACHER,
                        ArrayList(listOf(5))
                    )
                )
                list.add(
                    UserBean(
                        6,
                        "100001",
                        "wu",
                        "123456",
                        Identity.STUDENT,
                        ArrayList(listOf(1,2))
                    )
                )
                list.add(
                    UserBean(
                        7,
                        "100002",
                        "zhen",
                        "123456",
                        Identity.STUDENT,
                        ArrayList(listOf(3,4,5))
                    )
                )
                return list
            }
    }
}

enum class Identity(val IDENTITY: String) {
    TEACHER("teacher"),
    STUDENT("student")
}