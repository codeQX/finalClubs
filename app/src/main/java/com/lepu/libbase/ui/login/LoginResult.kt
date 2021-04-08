package com.lepu.libbase.ui.login

import com.lepu.android.common.data.model.UserInfo

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: UserInfo? = null,
    val error: Int? = null
)