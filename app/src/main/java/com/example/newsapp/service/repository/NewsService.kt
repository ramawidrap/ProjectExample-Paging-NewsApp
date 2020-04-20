package com.example.newsapp.service.repository

import com.example.newsapp.model.News
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything")
    fun getNews(@Query("q") q: String,
                @Query("apiKey") apiKey: String,
                @Query("page") page: Int,
                @Query("pageSize") pageSize: Int
    ): Flowable<NewsResponse>
}