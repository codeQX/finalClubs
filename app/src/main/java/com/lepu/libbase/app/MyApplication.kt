package com.lepu.libbase.app

import android.util.Log
import com.lepu.android.common.app.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : BaseApplication() {
    private val TAG: String = MyApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: app")

    }
}