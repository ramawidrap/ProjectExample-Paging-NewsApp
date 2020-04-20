package com.example.newsapp.service.repository

import com.example.newsapp.model.News
import com.google.gson.annotations.SerializedName

data class NewsResponse (

    @SerializedName("status")
    val status : String,

    @SerializedName("totalResults")
    val totalResults : Int,

    @SerializedName("articles")
    val articles : List<News>
    )