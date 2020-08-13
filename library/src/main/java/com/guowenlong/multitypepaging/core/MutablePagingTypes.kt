package com.guowenlong.multitypepaging.core

/**
 * @Description: 多类型带Paging的类型
 * @Author:      郭文龙
 * @Date:        2020/8/10 11:52 AM
 * @Email:       gavin.guo@tron.network
 */
open class MutablePagingTypes constructor(
        open val initialCapacity: Int = 0,
        open val types: MutableList<PagingType<*>> = ArrayList(initialCapacity)
) : PagingTypes {

    override val size: Int get() = types.size

    override fun <T> register(type: PagingType<T>) {
        types.add(type)
    }

    override fun unregister(clazz: Class<*>): Boolean {
        return types.removeAll { it.clazz == clazz }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getType(index: Int): PagingType<T> = types[index] as PagingType<T>

    override fun firstIndexOf(clazz: Class<*>): Int {
        val index = types.indexOfFirst { it.clazz == clazz }
        if (index != -1) {
            return index
        }
        return types.indexOfFirst { it.clazz.isAssignableFrom(clazz) }
    }
}
