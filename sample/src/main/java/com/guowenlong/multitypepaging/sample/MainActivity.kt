package com.guowenlong.multitypepaging.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.guowenlong.multitypepaging.MultiTypePagingAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy { MultiTypePagingAdapter() }

    private val contentDelegate by lazy {
        object : ContentViewDelegate() {
            override fun onItemClick(data: Content, position: Int) {
                Toast.makeText(this@MainActivity, "数据是${data.value},位置是$position",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val footDelegate by lazy {
        object : FooterViewDelegate(){
            override fun onItemClick() {
                Toast.makeText(this@MainActivity, "脚布局被点击了",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPaging()
    }

    private fun initPaging() {
        adapter.register(footDelegate)
        adapter.register(contentDelegate)
        rv_content.adapter = adapter.withLoadStateFooter(
            footer = DefaultLoadStateAdapter()
        )
        srl_content.setOnRefreshListener { adapter.refresh() }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                srl_content.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}