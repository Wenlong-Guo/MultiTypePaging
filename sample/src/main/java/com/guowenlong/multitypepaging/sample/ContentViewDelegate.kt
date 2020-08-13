package com.guowenlong.multitypepaging.sample

import com.guowenlong.multitypepaging.core.PagingData
import com.guowenlong.multitypepaging.sample.base.BaseItemViewDelegate
import com.guowenlong.multitypepaging.sample.databinding.ContentBinding
import com.guowenlong.multitypepaging.sample.databinding.FooterBinding

/**
 * @Description: TODO
 * @Author:      郭文龙
 * @Date:        2020/8/13 5:08 PM
 * @Email:       gavin.guo@tron.network
 */
abstract class ContentViewDelegate :
    BaseItemViewDelegate<ContentViewDelegate.Content, ContentBinding>() {

    override val itemLayoutId = R.layout.content

    abstract fun onItemClick(data: Content, position: Int)

    override fun bindData(binding: ContentBinding, data: Content, position: Int) {
        binding.tvName.text = "item的数字是${data.value},位置是$position"
        binding.root.setOnClickListener {
            onItemClick(data, position)
        }
    }

    class Content(val value: String) : PagingData()
}