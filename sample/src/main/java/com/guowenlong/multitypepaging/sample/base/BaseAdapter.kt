package com.guowenlong.multitypepaging.sample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description: [RecyclerView.Adapter]的基类
 * @Author:      郭文龙
 * @Date:        2020/8/13 4:08 PM
 * @Email:       gavin.guo@tron.network
 */
abstract class BaseAdapter<T, BD : ViewDataBinding>(var data: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder<BD>>() {

    protected abstract val itemLayoutId: Int

    override fun getItemCount() = data.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): BaseViewHolder<BD> {
        val itemDataBinding = DataBindingUtil.inflate<BD>(
            LayoutInflater.from(parent.context),
            itemLayoutId,
            parent,
            false
        )
        return BaseViewHolder(itemDataBinding.root, itemDataBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BD>, position: Int) {
        bindData(holder, position, data[position], holder.itemDataBinding)
        holder.itemDataBinding.executePendingBindings()
    }

    protected abstract fun bindData(
        holder: BaseViewHolder<BD>,
        position: Int,
        itemData: T,
        itemDataBinding: BD
    )
}