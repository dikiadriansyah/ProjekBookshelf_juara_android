package com.example.projekbookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.projekbookshelf.ui.screens.detail.DetailScreen
import com.example.projekbookshelf.ui.screens.home.HomeScreen
import com.example.projekbookshelf.ui.screens.splash.SplashScreen
import com.example.projekbookshelf.ui.viewModel.BookViewModel

@Composable
fun NavigationBuilder(){
    val viewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = AppScreen.SplashScreen.route
    ){
        composable(route = AppScreen.SplashScreen.route){
            SplashScreen(navController = navigationController)
        }
        composable(route= AppScreen.HomeScreen.route){
            HomeScreen(
                viewModel = viewModel,
                retryAction = viewModel::getAllBooks,
                navController = navigationController
            )
        }
        composable(route = AppScreen.DetailScreen.route){
            DetailScreen(
                navController = navigationController,
                viewModel = viewModel
            )
        }

    }
}