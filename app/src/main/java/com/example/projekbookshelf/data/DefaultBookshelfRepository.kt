package com.example.projekbookshelf.data

import com.example.projekbookshelf.model.Book
import com.example.projekbookshelf.network.BookshelfApiService

class DefaultBookshelfRepository(
    private  val bookshelfApiService: BookshelfApiService
): BookshelfRepository {
    override suspend fun getBooks(query: String): List<Book>?{
        return try{
            val res = bookshelfApiService.getBooks(query, "AIzaSyB6S6YW3yq5-1hz5lJ8hzIcKhPbQV39nJs")
        if(res.isSuccessful){
            res.body()?.items ?: emptyList()
        }else{
            emptyList()
        }
        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBook(id: String): Book? {
        return try{
            val res = bookshelfApiService.getBook(id,"AIzaSyB6S6YW3yq5-1hz5lJ8hzIcKhPbQV39nJs")
            if(res.isSuccessful){
                res.body()
            }else{
                null
            }
        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }
}