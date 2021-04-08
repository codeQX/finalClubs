package com.lepu.android.common.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class UserInfo(
    val userId: String,
    val displayName: String
){
    constructor(displayName: String):this("", displayName)
}