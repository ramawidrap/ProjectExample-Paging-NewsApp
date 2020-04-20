package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.ListNewsAdapter
import com.example.newsapp.model.News
import com.example.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_list_news.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ListNewsFragment : Fragment() {

    companion object {
        private const val TAG = "Fragment"
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : NewsViewModel

    lateinit var listNews : PagedList<News>

    lateinit var newsAdapter: ListNewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_news, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.mainComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(NewsViewModel::class.java)

//        viewModel.getNews().observe(this.viewLifecycleOwner, Observer {
//            listNews = it
//            initUI()
//        })

        viewModel.liveDataPagedList.observe(this.viewLifecycleOwner, Observer {
            Log.i(TAG, "loadMoreData");
            listNews = it
            initUI()
        })
    }

    fun initUI() {
        newsAdapter = ListNewsAdapter(this.context!!)
        newsAdapter.submitList(listNews)
        rv_listNews.layoutManager = LinearLayoutManager(this.context)
        rv_listNews.adapter = newsAdapter
//        newsAdapter.notifyDataSetChanged()
    }

}
