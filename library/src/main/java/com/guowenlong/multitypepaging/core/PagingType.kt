package com.guowenlong.multitypepaging.core

import com.drakeet.multitype.Linker
import com.guowenlong.multitypepaging.ItemViewDelegate

/**
 * @Description: MultiType的Type的改版
 * @Author:      郭文龙
 * @Date:        2020/8/10 11:49 AM
 * @Email:       gavin.guo@tron.network
 */
data class PagingType<T>(
    val clazz: Class<out T>,
    val delegate: ItemViewDelegate<T, *>,
    val linker: Linker<T>
)
