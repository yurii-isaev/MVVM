package com.github.mvvm.drawer

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView


class DrawerAdapter : RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private val items: List<DrawerItem>
    private val viewTypes: MutableMap<Class<out DrawerItem?>, Int>
    private val holderFactories: SparseArray<DrawerItem>
    private val listener: AdapterView.OnItemClickListener? = null

    constructor(items: List<DrawerItem>) : super() {
        this.items = emptyList()
        viewTypes = HashMap<Class<out DrawerItem?>, Int>()
        holderFactories = SparseArray<DrawerItem>()
        processViewTypes()
    }

    private fun processViewTypes() {
        var type = 0
        for (item in items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes[item.getClass()] = type
                holderFactories.put(type, item)
                type++
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder: ViewHolder = holderFactories[viewType].createViewHolder(parent)
        holder.drawerAdapter = this
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypes[items[position].getClass()]!!
    }


    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var drawerAdapter: DrawerAdapter? = null

        override fun onClick(v: View) {}

        init {
            itemView.setOnClickListener(this)
        }
    }
}