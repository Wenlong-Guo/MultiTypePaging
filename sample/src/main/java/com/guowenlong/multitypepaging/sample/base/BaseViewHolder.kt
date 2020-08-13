package com.guowenlong.multitypepaging.sample.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description: [RecyclerView.ViewHolder]的基类
 * @Author:      郭文龙
 * @Date:        2020/8/13 4:00 PM
 * @Email:       gavin.guo@tron.network
 */
class BaseViewHolder<T : ViewDataBinding>(itemView: View, var itemDataBinding: T) : RecyclerView.ViewHolder(itemView)