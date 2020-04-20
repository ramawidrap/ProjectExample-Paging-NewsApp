package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newsapp.model.News
import com.example.newsapp.service.repository.NewsRepository
import com.example.newsapp.source.DataSourceFactory
import java.util.concurrent.Executor
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository,private val dataSourceFactory: DataSourceFactory,private val executor: Executor) : ViewModel() {
    lateinit var liveDataPagedList :  LiveData<PagedList<News>>
    init {
        initPaging()
    }

    fun initPaging() {
        val pagedList : PagedList.Config = PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(15).setPageSize(10).build()


        liveDataPagedList = LivePagedListBuilder(dataSourceFactory,pagedList).setFetchExecutor(executor).build()

    }

    fun getNews() : LiveData<List<News>> {
        return newsRepository.listNewsMutableLiveData
    }

    fun getError() : LiveData<Throwable> {
        return newsRepository.errorMutableLiveData
    }

    fun clear() {
        newsRepository.clear()
    }
}