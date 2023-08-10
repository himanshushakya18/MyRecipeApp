package com.example.myrecipeapp.presentation.MealSearch

import com.example.myrecipeapp.domain.model.Meal

data class MealSearchState(
    val data:List<Meal>?=null,
    val error:String = "",
    val isLoading:Boolean = false
)
