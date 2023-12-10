package com.example.projekbookshelf.di

import com.example.projekbookshelf.data.BookshelfRepository
import com.example.projekbookshelf.network.BookshelfApiService

interface AppContainer {
    val bookshelfApiService: BookshelfApiService
    val bookshelfRepository: BookshelfRepository
}