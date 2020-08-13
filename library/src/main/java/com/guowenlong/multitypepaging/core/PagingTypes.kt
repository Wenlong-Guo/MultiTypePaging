package com.guowenlong.multitypepaging.core

import com.drakeet.multitype.Type
import com.drakeet.multitype.Types

/**
 * @Description: [Types] 的改版
 * @Author:      郭文龙
 * @Date:        2020/8/10 11:51 AM
 * @Email:       gavin.guo@tron.network
 */
interface PagingTypes {
    /**
     * Returns the size of the [Types].
     */
    val size: Int

    fun <T> register(type: PagingType<T>)

    /**
     * Unregister all types indexed by the specified class.
     *
     * @param clazz the main class of a [Type]
     * @return true if any types are unregistered from this [Types]
     */
    fun unregister(clazz: Class<*>): Boolean

    /**
     * Gets the type at the specified index.
     *
     * @param index the type index
     * @return the [Type] at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    fun <T> getType(index: Int): PagingType<T>

    /**
     * For getting index of the type class. If the subclass is already registered,
     * the registered mapping is used. If the subclass is not registered, then look
     * for its parent class if is registered, if the parent class is registered,
     * the subclass is regarded as the parent class.
     *
     * @param clazz the type class.
     * @return The index of the first occurrence of the specified class in this [Types],
     * or -1 if this [Types] does not contain the class.
     */
    fun firstIndexOf(clazz: Class<*>): Int

}