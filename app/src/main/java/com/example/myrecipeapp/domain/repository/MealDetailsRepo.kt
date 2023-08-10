package com.example.myrecipeapp.domain.repository

import com.example.myrecipeapp.data.model.MealsDTO

interface MealDetailsRepo {
    suspend fun getMealDetails(id:String): MealsDTO
}