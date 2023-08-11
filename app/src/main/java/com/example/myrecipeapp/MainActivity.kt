package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.data.remote.MealApi
import com.example.myrecipeapp.data.repository.MealSearchRepoImpl
import com.example.myrecipeapp.domain.repository.MealSearchRepo
import com.example.myrecipeapp.domain.use_case.GetMealsUseCase
import com.example.myrecipeapp.presentation.MealDetail.DetailScreen
import com.example.myrecipeapp.presentation.MealSearch.MealSearchViewModel
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme
import com.example.myrecipeapp.presentation.MealSearch.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            navigate()
//            SearchScreen(rememberNavController())
        }
    }
}