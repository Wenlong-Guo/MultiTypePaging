package com.guowenlong.multitypepaging.sample

import com.guowenlong.multitypepaging.core.PagingData
import com.guowenlong.multitypepaging.sample.base.BaseItemViewDelegate
import com.guowenlong.multitypepaging.sample.databinding.FooterBinding

/**
 * @Description: 脚布局
 * @Author:      郭文龙
 * @Date:        2020/8/11 12:07 PM
 * @Email:       gavin.guo@tron.network
 */
abstract class FooterViewDelegate :
    BaseItemViewDelegate<FooterViewDelegate.FootPagingData, FooterBinding>() {

    override val itemLayoutId = R.layout.footer

    abstract fun onItemClick()

    override fun bindData(binding: FooterBinding, data: FootPagingData, position: Int) {
        binding.root.setOnClickListener {
            onItemClick()
        }
    }

    class FootPagingData(val value: String) : PagingData()
}
