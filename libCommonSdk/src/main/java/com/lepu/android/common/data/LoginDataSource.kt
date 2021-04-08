package com.lepu.android.common.data

import com.lepu.android.common.data.model.UserInfo
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<UserInfo> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = UserInfo(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}