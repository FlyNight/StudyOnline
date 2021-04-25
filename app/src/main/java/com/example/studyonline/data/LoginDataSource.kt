package com.example.studyonline.data

import com.example.studyonline.data.bean.Identity
import com.example.studyonline.data.holder.UserHolder
import com.example.studyonline.data.model.LoggedInUser
import java.io.IOException
import kotlin.properties.Delegates

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        var flag: Int = 0
        var tmpId by Delegates.notNull<Int>()
        lateinit var tmpAccount: String
        lateinit var tmpName: String
        lateinit var tmpIdentity: Identity
        val thread = Thread {
            for (element in UserHolder.getDataFromDatabase()) {
                if (username == element.userId && password == element.userPassword) {
                    flag = 1
                    tmpId = element.id
                    tmpAccount = element.userId
                    tmpName = element.userName
                    tmpIdentity = element.userIdentity
                }
            }
        }
        thread.start()
        thread.join()
        return if (flag == 0) {
            Result.Error(IOException("Wrong Log In"))
        } else {
            val fakeUser = LoggedInUser(tmpId, tmpAccount, tmpName, tmpIdentity)
            Result.Success(fakeUser)
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}