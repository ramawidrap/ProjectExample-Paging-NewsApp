package com.example.newsapp.di

import com.example.newsapp.service.repository.NewsService
import com.example.newsapp.service.repository.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()).baseUrl(
            RetrofitService.BASEURL
        ).build()
    }

    @Singleton
    @Provides
    fun getNewsService(retrofit: Retrofit) : NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun getExecutor() : Executor {
        return Executors.newFixedThreadPool(5)
    }

}