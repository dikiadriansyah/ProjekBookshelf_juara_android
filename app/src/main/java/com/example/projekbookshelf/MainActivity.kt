package com.example.projekbookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projekbookshelf.navigation.NavigationBuilder

import com.example.projekbookshelf.ui.BooksApp
import com.example.projekbookshelf.ui.theme.ProjekBookshelfTheme

class MainActivity : ComponentActivity() {

//    companion object{
//        var API_KEY = ""
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Set APi Key
//        API_KEY = resources.getString(R.string.book_api_key)

        setContent {
            ProjekBookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationBuilder()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjekBookshelfTheme {
        BooksApp()
    }
}