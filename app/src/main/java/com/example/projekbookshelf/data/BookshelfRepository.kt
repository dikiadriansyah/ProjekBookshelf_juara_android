package com.example.projekbookshelf.data

import com.example.projekbookshelf.model.Book

interface BookshelfRepository{
    suspend fun getBooks(query: String): List<Book>?

    suspend fun getBook(id: String): Book?
}