package com.lepu.android.common.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.core.LoadSir
import com.lepu.android.common.base.callback.EmptyCallback
import com.lepu.android.common.base.callback.ErrorCallback
import com.lepu.android.common.base.callback.LoadingCallback
import com.lepu.android.lib.common.BuildConfig

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initLoadSir()

        initRouter()

    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun initLoadSir() {

        LoadSir.beginBuilder()
            //添加各种状态页
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
//            .addCallback(TimeoutCallback())
//            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
    }


}