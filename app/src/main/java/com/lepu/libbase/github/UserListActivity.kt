package com.lepu.libbase.github

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.lepu.android.common.base.BaseStateActivity
import com.lepu.android.common.data.model.UserGithub
import com.lepu.android.common.http.api.ApiDemo
import com.lepu.libbase.R
import com.lepu.libbase.databinding.ActivityUserListBinding
import com.lepu.libbase.vm.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class UserListActivity(override val mLayoutId: Int = R.layout.activity_user_list) :
    BaseStateActivity<UserListViewModel, ActivityUserListBinding>() {

    @Inject
    lateinit var apiDemo: ApiDemo

    var userLivePagedList: LiveData<PagedList<UserGithub>>? = null

    override fun initView() {

    }

    override fun initData() {
        mDataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.HORIZONTAL
            )
        )
        val adapter = UserListAdapter()
        mDataBinding.recyclerView.adapter = adapter

        val list = apiDemo.getUserList()

        list.enqueue(object : Callback<List<UserGithub>> {
            override fun onResponse(
                call: Call<List<UserGithub>>,
                response: Response<List<UserGithub>>
            ) {

                Log.i(TAG, "onResponse: list:${response.body()}")
            }

            override fun onFailure(call: Call<List<UserGithub>>, t: Throwable) {
                Log.i(TAG, "onFailure: msg:${t.message}")
            }

        })

        Handler().postDelayed({
            showContent()
        }, 1000)
//        userLivePagedList = LivePagedListBuilder(apiDemo.getUserList(), 20).build()
//
//        userLivePagedList?.observe(this){
//            adapter.submitList(it)
//        }
    }

    override fun getViewModel(): UserListViewModel {
        return ViewModelProvider(this).get(UserListViewModel::class.java)
    }

}