package com.guowenlong.multitypepaging

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * @Description: 多类型带Paging的Item代理类
 * @Author:      郭文龙
 * @Date:        2020/8/10 10:53 AM
 * @Email:       gavin.guo@tron.network
 */
abstract class ItemViewDelegate<T, VH : ViewHolder> {

    @Suppress("PropertyName")
    internal var _adapter: MultiTypePagingAdapter? = null

    val adapter: MultiTypePagingAdapter
        get() {
            if (_adapter == null) {
                throw IllegalStateException(
                        "This $this has not been attached to MultiTypeAdapter yet. " +
                                "You should not call the method before registering the delegate."
                )
            }
            return _adapter!!
        }

    abstract fun onCreateViewHolder(context: Context, parent: ViewGroup): VH

    abstract fun onBindViewHolder(holder: VH, item: T)

    open fun onBindViewHolder(holder: VH, item: T, payloads: List<Any>) {
        onBindViewHolder(holder, item)
    }

    fun getPosition(holder: ViewHolder): Int {
        return holder.bindingAdapterPosition
    }

    @Suppress("UNUSED_PARAMETER")
    open fun getItemId(item: T): Long = RecyclerView.NO_ID

    open fun onViewRecycled(holder: VH) {}

    open fun onFailedToRecycleView(holder: VH): Boolean {
        return false
    }

    open fun onViewAttachedToWindow(holder: VH) {}

    open fun onViewDetachedFromWindow(holder: VH) {}
}
