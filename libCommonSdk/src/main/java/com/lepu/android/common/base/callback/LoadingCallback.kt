package com.lepu.android.common.base.callback

import com.kingja.loadsir.callback.Callback
import com.lepu.android.lib.common.R

class LoadingCallback:Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }
}