package com.guowenlong.multitypepaging.sample

import com.guowenlong.multitypepaging.sample.base.BaseLoadStateAdapter
import com.guowenlong.multitypepaging.sample.base.BaseViewHolder
import com.guowenlong.multitypepaging.sample.databinding.LoadingBinding

/**
 * @Description: Paging的loading样式
 * @Author:      郭文龙
 * @Date:        2020/8/11 11:48 AM
 * @Email:       gavin.guo@tron.network
 */
class DefaultLoadStateAdapter : BaseLoadStateAdapter<LoadingBinding>() {

    override val itemLayoutId = R.layout.loading

    override fun bindData(holder: BaseViewHolder<LoadingBinding>, binding: LoadingBinding) {

    }
}