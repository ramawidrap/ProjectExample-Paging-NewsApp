package com.example.newsapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import com.example.newsapp.source.DataSourceFactory
import com.example.newsapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun getViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

}
