package com.lepu.libbase

import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.lepu.android.common.base.BaseStateActivity
import com.lepu.android.common.http.api.ApiIXZ
import com.lepu.libbase.databinding.ActivityMainBinding
import com.lepu.libbase.github.UserListActivity
import com.lepu.libbase.ui.login.LoginActivity
import com.lepu.libbase.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity(override val mLayoutId: Int = R.layout.activity_main) :
    BaseStateActivity<MainViewModel, ActivityMainBinding>() {

    private var mNum: ObservableInt = ObservableInt(5)

    @Inject
    lateinit var mApiIXZ: ApiIXZ

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun initView() {
        mDataBinding.num = mNum
    }

    override fun initData() {
        Handler().postDelayed({
            showEmpty()
        }, 1000)
        mViewModel.liveDataString.observe(this) {
            mDataBinding.tv1.text = it
        }
        Log.i(TAG, "initData: api $mApiIXZ")

    }


    fun onLike(view: View) {
        showLoading()

        mNum.set(mNum.get() + 1)
        mViewModel.liveDataString.value = mNum.get().toString() + "sss"

        Handler().postDelayed({hideLoading()}, 2000)

        startActivity(Intent(this, UserListActivity::class.java))
    }

    override fun reload() {
        super.reload()
        showContent()
    }

}