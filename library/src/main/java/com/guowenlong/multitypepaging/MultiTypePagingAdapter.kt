package com.guowenlong.multitypepaging

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.guowenlong.multitypepaging.core.*
import com.guowenlong.multitypepaging.core.PagingLinker

/**
 * @Description: 多类型分页适配器
 * @Author:      郭文龙
 * @Date:        2020/8/10 11:22 AM
 * @Email:       gavin.guo@tron.network
 */
open class MultiTypePagingAdapter(
    open val initialCapacity: Int = 0,
    open var types: PagingTypes = MutablePagingTypes(
        initialCapacity
    ),
    diffCallback: DiffUtil.ItemCallback<PagingData>? = null
) : PagingDataAdapter<PagingData, RecyclerView.ViewHolder>(
        diffCallback ?: object : DiffUtil.ItemCallback<PagingData>() {
            override fun areItemsTheSame(oldItem: PagingData, newItem: PagingData) = oldItem == newItem
            override fun areContentsTheSame(oldItem: PagingData, newItem: PagingData) = oldItem == newItem
        }
) {
    inline fun <reified T : PagingData> register(binder: ItemViewDelegate<T, *>) {
        register(T::class.java, binder)
        itemCount
    }

    fun <T> register(clazz: Class<T>, delegate: ItemViewDelegate<T, *>) {
        unregisterAllTypesIfNeeded(clazz)
        register(
            PagingType(
                clazz,
                delegate,
                PagingLinker()
            )
        )
    }

    internal fun <T> register(type: PagingType<T>) {
        types.register(type)
        type.delegate._adapter = this
    }

    override fun getItemViewType(position: Int): Int {
        return indexInTypesOf(position, getItem(position) as Any)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return types.getType<Any>(viewType).delegate.onCreateViewHolder(parent.context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.bindingAdapterPosition >= 0) getOutDelegateByViewHolder(holder).onBindViewHolder(holder, getItem(position) as Any, emptyList())
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.bindingAdapterPosition >= 0) getOutDelegateByViewHolder(holder).onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return getOutDelegateByViewHolder(holder).onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.bindingAdapterPosition >= 0) getOutDelegateByViewHolder(holder).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.bindingAdapterPosition >= 0) getOutDelegateByViewHolder(holder).onViewDetachedFromWindow(holder)
    }

    private fun unregisterAllTypesIfNeeded(clazz: Class<*>) {
        if (types.unregister(clazz)) Log.w("MultiTypePagingAdapter","The type ${clazz.simpleName} you originally registered is now overwritten.")
    }

    private fun indexInTypesOf(position: Int, item: Any): Int {
        val index = types.firstIndexOf(item.javaClass)
        if (index != -1) {
            val linker = types.getType<Any>(index).linker
            return index + linker.index(position, item)
        }
        throw RuntimeException("Have you registered the ${item.javaClass.name} type and its delegate or binder?")
    }

    private fun getOutDelegateByViewHolder(holder: RecyclerView.ViewHolder): ItemViewDelegate<Any, RecyclerView.ViewHolder> {
        @Suppress("UNCHECKED_CAST")
        return types.getType<Any>(getItemViewType(holder.bindingAdapterPosition)).delegate as ItemViewDelegate<Any, RecyclerView.ViewHolder>
    }
}