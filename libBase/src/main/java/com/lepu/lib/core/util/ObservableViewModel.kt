package com.lepu.lib.core.util

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

/**
 * 带Observable的ViewModel，配合DataBinding使用
 */
open class ObservableViewModel:ViewModel(), Observable {

    private val callbacks:PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    /**
     * 通知所有监听者（观察者），实例的所有属性已经改变
     */
    fun notifyChange(){
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * 通知所有监听者，特定的属性改变了。这个属性的getter方法应该用[#Bindable]注解，才能在BR里面生成一个field
     * ，作为fieldId。
     *
     * @param fieldId 为这个#Bindable field生成的fieldId
     *
     */
    fun notifyPropertyChanged(fieldId: Int){
        callbacks.notifyCallbacks(this, fieldId, null)
    }

}