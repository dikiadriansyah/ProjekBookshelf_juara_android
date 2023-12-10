package com.example.projekbookshelf.ui

import android.app.Application
import com.example.projekbookshelf.di.AppContainer
import com.example.projekbookshelf.di.DefaultAppContainer

class BooksApp: Application(){
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }

}