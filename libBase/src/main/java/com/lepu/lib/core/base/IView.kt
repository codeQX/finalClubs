package com.lepu.lib.core.base

interface IView {

    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun hideLoading()

    /**
     * 显示toast提示信息
     */
    fun showMessage(msg: String)

}