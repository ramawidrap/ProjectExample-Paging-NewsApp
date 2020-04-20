package com.example.newsapp.source

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.newsapp.model.News
import com.example.newsapp.service.repository.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDataSource @Inject constructor(private val newsService: NewsService)  : PageKeyedDataSource<Int, News>() {


    enum class Network {
        LOADING,
        SUCCESS,
        FAIL
    }

    val networkStatus = MutableLiveData<Network>()


    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        networkStatus.postValue(Network.LOADING)
        newsService.getNews("sports","aa67d8d98c8e4ad1b4f16dbd5f3be348",1,15).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            Consumer {
                callback.onResult(it.articles,null,2)
                networkStatus.postValue(Network.SUCCESS)
                Consumer<Throwable> {
                    networkStatus.postValue(Network.FAIL)
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        networkStatus.postValue(Network.LOADING)
        newsService.getNews("sports","aa67d8d98c8e4ad1b4f16dbd5f3be348",params.key,10).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            Consumer {
                Log.i("Fragment","Load More Data")

                callback.onResult(it.articles,params.key+1)
                networkStatus.postValue(Network.SUCCESS)


            },
            Consumer<Throwable>{
                Log.i("Fragment",it.message)
                networkStatus.postValue(Network.FAIL)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }
}