package com.guowenlong.multitypepaging.sample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * @Description: [LoadStateAdapter] 带 DataBinding 的基类
 * @Author:      郭文龙
 * @Date:        2020/8/13 5:00 PM
 * @Email:       gavin.guo@tron.network
 */
abstract class BaseLoadStateAdapter<BD : ViewDataBinding> : LoadStateAdapter<BaseViewHolder<BD>>() {
    protected abstract val itemLayoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseViewHolder<BD> {
        DataBindingUtil.inflate<BD>(LayoutInflater.from(parent.context), itemLayoutId, parent, false).also {
            return BaseViewHolder(it.root, it)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BD>, loadState: LoadState) {
        (holder as? BaseViewHolder<BD>)?.also {
            bindData(it, it.itemDataBinding)
            holder.itemDataBinding.executePendingBindings()
        }
    }

    /**
     * 绑定holder的数据
     *
     * @param holder   子holder
     * @param binding item对应的 data binding
     */
    protected abstract fun bindData(
        holder: BaseViewHolder<BD>,
        binding: BD
    )
}