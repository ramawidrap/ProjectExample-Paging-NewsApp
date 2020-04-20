package com.example.newsapp.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.News
import kotlinx.android.synthetic.main.list_news.view.*

class ListNewsAdapter(private val context : Context ) : PagedListAdapter<News,ListNewsAdapter.listNewsViewHolder>(News.CALLBACK) {


    class listNewsViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val tv_title = itemView.tv_title
        val tv_author = itemView.tv_author
        val iv_poster = itemView.iv_poster

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listNewsViewHolder {
        return listNewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_news,parent,false))
    }

    override fun submitList(pagedList: PagedList<News>?) {
        Log.i("Fragment","submitlist ${pagedList}")
        super.submitList(pagedList)
    }

    override fun onBindViewHolder(holder: listNewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.tv_title.text = news!!.title
        holder.tv_author.text = news!!.author
        try{
            Glide.with(context).load(news!!.urlImage).placeholder(R.drawable.ic_launcher_background).into(holder.iv_poster)

        }
        catch (t: Throwable) {
            Glide.with(context).load(R.drawable.ic_launcher_background).placeholder(R.drawable.ic_launcher_background).into(holder.iv_poster)
        }


    }

}