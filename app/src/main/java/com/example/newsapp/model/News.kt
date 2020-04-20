package com.example.newsapp.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(

    @SerializedName("author")
    val author : String,

    @SerializedName("title")
    val title : String,

    @SerializedName("urlToImage")
    val urlImage : String
) : Parcelable {
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return true
            }
        }
    }
}