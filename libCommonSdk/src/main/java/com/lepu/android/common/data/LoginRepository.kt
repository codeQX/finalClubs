package com.lepu.android.common.data

import com.lepu.android.common.data.model.UserInfo

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var userInfo: UserInfo? = null
        private set

    val isLoggedIn: Boolean
        get() = userInfo != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        userInfo = null
    }

    fun logout() {
        userInfo = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<UserInfo> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(userInfo: UserInfo) {
        this.userInfo = userInfo
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}