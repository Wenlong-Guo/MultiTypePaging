package com.guowenlong.multitypepaging.core

import com.drakeet.multitype.Linker

/**
 * @Description: Paging的[Linker]
 * @Author:      郭文龙
 * @Date:        2020/8/10 11:58 AM
 * @Email:       gavin.guo@tron.network
 */
internal class PagingLinker<T> : Linker<T> {
    override fun index(position: Int, item: T): Int = 0
}