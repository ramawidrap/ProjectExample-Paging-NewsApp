package com.example.newsapp.service.repository

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsService: NewsService) {

    val listNewsMutableLiveData = MutableLiveData<List<News>>()

    val errorMutableLiveData = MutableLiveData<Throwable>()

    val compositeDisposable = CompositeDisposable()

    init {
        getDataNews()
    }

    fun getDataNews() {
        compositeDisposable.add(newsService.getNews("sports","aa67d8d98c8e4ad1b4f16dbd5f3be348",1,10).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe{
            listNewsMutableLiveData.postValue(it.articles)

            Consumer<Throwable> {
                errorMutableLiveData.postValue(it)
            }
        })
    }

    fun clear() {
        compositeDisposable.clear()
    }
}