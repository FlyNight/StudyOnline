package com.example.studyonline.ui.login

import com.example.studyonline.data.bean.Identity

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val displayId: String,
    val displayIdentity: Identity
    //... other data fields that may be accessible to the UI
)