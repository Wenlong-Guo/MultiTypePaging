package com.guowenlong.multitypepaging.sample

import kotlinx.coroutines.delay

/**
 * @Description: Main 的 Repository
 * @Author:      郭文龙
 * @Date:        2020/8/13 4:56 PM
 * @Email:       gavin.guo@tron.network
 */
object MainRepository {
    private const val length = 100

    private val data = mutableListOf<Int>().also {
        for (index in 0..length) {
            it.add(index)
        }
    }

    suspend fun getData(start: Int, limit: Int): MutableList<Int> {
        delay(1000)
        if (start >= length) return mutableListOf()
        return data.subList(start, start + limit)
    }
}