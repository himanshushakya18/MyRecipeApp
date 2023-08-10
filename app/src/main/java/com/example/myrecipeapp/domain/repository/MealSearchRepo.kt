package com.example.myrecipeapp.domain.repository

import com.example.myrecipeapp.data.model.MealsDTO

interface MealSearchRepo {
    suspend fun getMealList(s:String): MealsDTO
}