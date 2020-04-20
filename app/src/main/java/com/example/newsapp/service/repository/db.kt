package com.example.newsapp.service.repository

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASEURL = "https://newsapi.org"

    var getInstance : Retrofit? = null

    fun getRetorfit () : Retrofit {
        if(getInstance == null ) {

        }

        return getInstance!!
    }
}