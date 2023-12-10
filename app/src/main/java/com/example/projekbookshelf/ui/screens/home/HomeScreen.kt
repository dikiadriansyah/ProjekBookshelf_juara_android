package com.example.projekbookshelf.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projekbookshelf.R
import com.example.projekbookshelf.model.Book
import com.example.projekbookshelf.navigation.AppScreen
import com.example.projekbookshelf.ui.viewModel.BookViewModel
import com.example.projekbookshelf.ui.viewModel.QueryUiState
import com.example.projekbookshelf.ui.widgets.GoogleColorLine
import com.example.projekbookshelf.ui.widgets.GridItem
import com.example.projekbookshelf.ui.widgets.Loader


private val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: BookViewModel,
    retryAction: () -> Unit,
    navController: NavController
){
    val uiState = viewModel.uiState.collectAsState().value
    var queryString by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            GoogleColorLine()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .padding(16.dp),
                    painter = painterResource(id = R.drawable.google_book_logo),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Google Book Logo"
                )

                SearchBar(
                    modifier = Modifier.weight(1f),
                    query = queryString,
                    onQueryChange = { newQueryString ->
                        queryString = newQueryString
                        viewModel.getBooks(queryString)
                    },
                    onSearch = {},
                    placeholder = {
                        Text(text = "Search book here...")
                    },
                    leadingIcon = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    },
                    content = {},
                    active = false,
                    onActiveChange = {},
                    tonalElevation = 0.dp,
                )
            }

            when (uiState) {
                is QueryUiState.Loading -> {
                    Spacer(modifier = Modifier.weight(1f))

                    Loader(animFile = R.raw.book_animation)

                    Spacer(modifier = Modifier.weight(1f))
                }
                is QueryUiState.Success ->
                    BooksListScreen(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        bookshelfList = uiState.bookshelfList,
                        navController = navController,
                        viewModel = viewModel
                    )
                else -> ErrorScreen(modifier = Modifier.weight(1f), retryAction = retryAction)
            }
        }
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.e(TAG, "ERROR")

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = retryAction
        ) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
private fun BooksListScreen(
    viewModel: BookViewModel,
    navController: NavController,
    bookshelfList: List<Book>,
    modifier: Modifier = Modifier,
) {
//    Log.e(TAG, "LIST")

    if (bookshelfList.isEmpty()) {
        Column(
            modifier = modifier,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Loader(animFile = R.raw.no_data_animation)

            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp),
        ) {
            items(
                items = bookshelfList,
            ) { book ->
                GridItem(
                    book = book,
                    onDetailsClick = {
                        viewModel.selectedBook = book
                        navController.navigate(AppScreen.DetailScreen.route)
                    },
                )
            }
        }
    }
}
