package com.example.myrecipeapp.data.repository

import com.example.myrecipeapp.data.model.MealsDTO
import com.example.myrecipeapp.data.remote.MealApi
import com.example.myrecipeapp.domain.repository.MealDetailsRepo
import javax.inject.Inject

class MealDetailsRepoImpl @Inject constructor(private val mealApi: MealApi): MealDetailsRepo {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealApi.getMealDetails(id)
    }
}