package com.lepu.lib.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.lepu.lib.base.R
import com.lepu.lib.core.widget.LoadingPop

/**
 * activity基类，封装ViewModel，DataBinding
 */
abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : AppCompatActivity(), IView {
    protected val TAG: String = this::class.java.simpleName

    @get: LayoutRes
    abstract val mLayoutId: Int

    val mViewModel: VM by lazy {
        getViewModel()
    }


    val mDataBinding: DB by lazy {
        DataBindingUtil.setContentView(this, mLayoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView
        if (mLayoutId != 0) {
            initDataBinding()
        }

        loadingPop = LoadingPop(this)

        //初始化一些在initData中需要的东西，如：LoadService
        preInit()

        //初始化activity的数据
        initData()

    }

    /**
     * 在初始化InitData之前，如果有一些准备工作，可以在这里进行
     */
    abstract fun preInit()


    /**
     * 初始化数据绑定工作
     */
    private fun initDataBinding() {
        //bind data
        initView();

        //LiveData需要LifecycleOwner
        mDataBinding.lifecycleOwner = this
    }

    /**
     * like:
     * mDataBind.viewModel = mViewModel
     * mDataBind.user = mUser
     */
    abstract fun initView()

    abstract fun initData()

    abstract fun getViewModel(): VM

    //---------------------------IView------------------------------

    private lateinit var loadingPop: LoadingPop

    override fun showLoading() {
        showLoading(R.string.public_loading)

    }

    open fun showLoading(stringRes: Int) {
        showLoading(getString(stringRes))

    }

    open fun showLoading(string: String) {
        loadingPop.setMessage(string)
        if (!loadingPop.isShowing) {
            loadingPop.showPopupWindow()
        }
    }

    override fun hideLoading() {
        loadingPop.dismiss()
    }

    open fun showMessage(@StringRes stringRes: Int) {
        showMessage(getString(stringRes))
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}