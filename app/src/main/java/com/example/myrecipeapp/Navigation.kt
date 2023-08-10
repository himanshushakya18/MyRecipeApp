package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myrecipeapp.presentation.MealDetail.DetailScreen
import com.example.myrecipeapp.presentation.MealSearch.SearchScreen

enum class Screens {
    HOME,
    DETAILS
}

@Composable
fun navigate() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HOME.name) {
        composable(route = Screens.HOME.name) {
            SearchScreen (navController = navController)
        }
        composable(
            route = Screens.DETAILS.name,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){
            val id =it.arguments?.getString("id")
         DetailScreen(id = id?:"")
        }
    }
}

