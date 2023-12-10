package com.example.projekbookshelf.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projekbookshelf.data.BookshelfRepository
import com.example.projekbookshelf.model.Book
import com.example.projekbookshelf.ui.BooksApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface QueryUiState{
    data class Success(val bookshelfList: List<Book>): QueryUiState
    object Error: QueryUiState
    object Loading: QueryUiState
}

private  val TAG = "BookViewModel"

class BookViewModel(
    private val bookshelfRepository: BookshelfRepository
): ViewModel(){
    private val _uiState = MutableStateFlow<QueryUiState>(QueryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init{
        getAllBooks()
    }
    lateinit var selectedBook: Book

    fun getBooks(query: String = ""){
        viewModelScope.launch {
            _uiState.value = QueryUiState.Loading

            _uiState.value = try{
                val books = bookshelfRepository.getBooks(query)
                if(books == null){
                    QueryUiState.Error
                }else if(books.isEmpty()){
                    QueryUiState.Success(emptyList())
                }else{
                    QueryUiState.Success(books)
                }
            }catch(e: IOException){
                QueryUiState.Error
            }catch(e: HttpException){
                QueryUiState.Error
            }
        }
    }

    fun getAllBooks(){
        Log.d(TAG, "Started to getAllBooks()...")

        val topics = arrayListOf(
            "Biology","Flower","Science","Soccer","Dog","Plant","Cooking","Recipe",
            "Human","Cat","Cow","Sugar","Junk Food", "Android","Kotlin","Java","iOS"
        )

        viewModelScope.launch{
            _uiState.value = QueryUiState.Loading
            _uiState.value = try{
                val books = bookshelfRepository.getBooks(topics.random())
                if(books == null){
                    Log.d(TAG, "Failed to getAllBooks()")
                    QueryUiState.Error
                }else if(books.isEmpty()){
                    Log.d(TAG, "Empty when getAllBooks()...")
                    QueryUiState.Success(emptyList())
                }else{
                    Log.d(TAG, "Success to getAllBooks(), data: $books")
                    QueryUiState.Success(books)
                }
            }catch(e: IOException){
                QueryUiState.Error
            }catch(e: HttpException){
                QueryUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[APPLICATION_KEY] as BooksApp)
                val bookshelfRepository = application.container.bookshelfRepository

                BookViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }

}