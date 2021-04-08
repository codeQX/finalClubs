package com.lepu.android.common.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.Convertor
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lepu.android.common.base.callback.EmptyCallback
import com.lepu.android.common.base.callback.ErrorCallback
import com.lepu.android.common.base.callback.LoadingCallback
import com.lepu.lib.core.base.BaseActivity
import com.lepu.lib.core.net.ResultStatus

/**
 * 可以切换状态的activity
 */
abstract class BaseStateActivity<VM : ViewModel, DB : ViewDataBinding> : BaseActivity<VM, DB>() {

    private lateinit var loadService: LoadService<Any>


    override fun preInit() {
        loadService = LoadSir.getDefault().register(this, {
            reload()
        }, Convertor<ResultStatus> { status ->
            return@Convertor when (status) {
                ResultStatus.SUCCESS -> {
                    SuccessCallback::class.java
                }
                ResultStatus.ERROR -> {
                    ErrorCallback::class.java
                }
                ResultStatus.LOADING -> {
                    LoadingCallback::class.java
                }
                else -> LoadingCallback::class.java
            }
        })
    }

    open fun reload() {
        loadService.showCallback(LoadingCallback::class.java)

    }

    open fun showContent() {
//        loadService.showCallback(SuccessCallback::class.java)
        loadService.showWithConvertor(ResultStatus.SUCCESS)

    }

    open fun showError() {
        loadService.showCallback(ErrorCallback::class.java)

    }

    open fun showEmpty() {
        loadService.showCallback(EmptyCallback::class.java)

    }


}