package com.example.newsapp.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp.model.News
import javax.inject.Inject

class DataSourceFactory @Inject constructor(private val newsDataSource: NewsDataSource) : DataSource.Factory<Int, News>() {
    val mutableLiveDataSource = MutableLiveData<NewsDataSource>()
    override fun create(): DataSource<Int, News> {

        mutableLiveDataSource.postValue(newsDataSource)
        return newsDataSource

    }

}