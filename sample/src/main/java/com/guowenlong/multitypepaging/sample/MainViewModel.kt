package com.guowenlong.multitypepaging.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.guowenlong.multitypepaging.core.PagingData
import kotlinx.coroutines.flow.map

/**
 * @Description: Main 的 ViewModel
 * @Author:      郭文龙
 * @Date:        2020/8/13 4:53 PM
 * @Email:       gavin.guo@tron.network
 */
class MainViewModel : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = 20, prefetchDistance = 20)) {
        object : PagingSource<Int, Int>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
                val page = params.key ?: 0
                val start = page * 10
                return try {
                    val response = MainRepository.getData(start, 10)
                    LoadResult.Page(
                        data = response,
                        prevKey = null,
                        nextKey = if (response.size == 0) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }.flow
        .map {
            it.map {
                ContentViewDelegate.Content(it.toString())
            }.insertSeparators<ContentViewDelegate.Content, PagingData> { before, after ->
                when {
                    before != null && after == null -> FooterViewDelegate.FootPagingData("脚")
                    else -> null
                }
            }
        }
        .cachedIn(viewModelScope)
}