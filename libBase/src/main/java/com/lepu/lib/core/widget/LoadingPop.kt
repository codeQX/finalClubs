package com.lepu.lib.core.widget

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.lepu.lib.base.R
import razerdp.basepopup.BasePopupWindow

class LoadingPop(context: Context) : BasePopupWindow(context) {

    init {
        setBackgroundColor(context.getColor(R.color.public_color_transparent))
        setOutSideDismiss(false)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_loading)
    }

    fun setMessage(msg: String) {
        val tv = findViewById<TextView>(R.id.tv_loading)
        tv.text = msg
    }
}