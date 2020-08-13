package com.guowenlong.multitypepaging.sample.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.guowenlong.multitypepaging.ItemViewDelegate

/**
 * @Description: [ItemViewDelegate]的基类
 * @Author:      郭文龙
 * @Date:        2020/8/13 4:35 PM
 * @Email:       gavin.guo@tron.network
 */
abstract class BaseItemViewDelegate<T, BD : ViewDataBinding> :
    ItemViewDelegate<T, BaseViewHolder<BD>>() {

    protected abstract val itemLayoutId: Int

    override fun onBindViewHolder(holder: BaseViewHolder<BD>, item: T) {
        bindData(holder.itemDataBinding, item, holder.layoutPosition)
        holder.itemDataBinding.executePendingBindings()
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup
    ): BaseViewHolder<BD> {
        val itemDataBinding = DataBindingUtil.inflate<BD>(
            LayoutInflater.from(parent.context),
            itemLayoutId,
            parent,
            false
        )
        return BaseViewHolder(itemDataBinding.root, itemDataBinding)
    }

    /**
     * 绑定holder的数据
     *
     * @param binding item对应的 data binding
     * @param data 实体数据
     * @param position 当前的位置
     */
    protected abstract fun bindData(
        binding: BD,
        data: T,
        position: Int
    )
}