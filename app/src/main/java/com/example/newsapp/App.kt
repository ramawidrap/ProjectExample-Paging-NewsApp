package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.AppModule
import com.example.newsapp.di.DaggerMainComponent
import com.example.newsapp.di.MainComponent

class App : Application() {

    lateinit var app : App

    companion object {
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        mainComponent = DaggerMainComponent.builder().appModule(AppModule(this)).build()
    }





}