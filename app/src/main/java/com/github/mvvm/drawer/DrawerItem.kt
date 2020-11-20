package com.github.mvvm.drawer

import android.view.ViewGroup

abstract class DrawerItem<T : DrawerAdapter.ViewHolder?> {

    private var isChecked: Boolean? = false

    abstract fun createViewHolder(parent: ViewGroup?): T

    abstract fun bindViewHolder(holder: T)

    fun setChecked(isChecked: Boolean): DrawerItem<T> {
        this.isChecked = isChecked
        return this
    }

    fun isChecked(): Boolean {
        return isChecked()
    }

    fun isSelectable(): Boolean {
        return true
    }
}