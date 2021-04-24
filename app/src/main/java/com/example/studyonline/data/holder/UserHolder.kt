package com.example.studyonline.data.holder

import com.example.studyonline.activitys.MainActivity.Companion.cn
import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.bean.UserBean

class UserHolder {
    companion object {
        fun getDataFromDatabase(): ArrayList<UserBean> {
            val data: ArrayList<UserBean> = ArrayList()
            val ps = cn.createStatement()
            val resultSet = ps.executeQuery("select * from users")
            while (resultSet.next()) {
                val ps1 = cn.createStatement()
                var resultSet1 = ps1.executeQuery("select * from users_identitys where user_id = ${resultSet.getInt("user_id")}")
                resultSet1.next()
                val identityId = resultSet1.getInt("identity_id")
                resultSet1 = ps1.executeQuery("select * from identitys where identity_id = $identityId")
                resultSet1.next()
                val identity = resultSet1.getString("identity")
                ps1.close()
                if(identity == Identity.TEACHER.IDENTITY) {
                    data.add(
                        UserBean(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_account"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_password"),
                            Identity.TEACHER
                        )
                    )
                } else {
                    data.add(
                        UserBean(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_account"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_password"),
                            Identity.STUDENT
                        )
                    )
                }

            }
            ps.close()
            return data
        }
    }
}