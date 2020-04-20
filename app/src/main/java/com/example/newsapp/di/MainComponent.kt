package com.example.newsapp.di

import com.example.newsapp.ListNewsFragment
import com.example.newsapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,RetrofitModule::class,ViewModelFactoryModule::class])
interface MainComponent {

    fun inject(listNewsFragment: ListNewsFragment)

}