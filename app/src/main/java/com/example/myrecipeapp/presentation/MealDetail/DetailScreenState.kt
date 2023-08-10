package com.example.myrecipeapp.presentation.MealDetail

import com.example.myrecipeapp.domain.model.Meal
import com.example.myrecipeapp.domain.model.MealDetails

data class DetailScreenState(
    val isLoading:Boolean = false,
    val data : MealDetails? = null,
    val error :String = ""
)
