package com.example.myrecipeapp.data.repository

import com.example.myrecipeapp.data.model.MealsDTO
import com.example.myrecipeapp.data.remote.MealApi
import com.example.myrecipeapp.domain.repository.MealSearchRepo
import javax.inject.Inject


class MealSearchRepoImpl @Inject constructor(private val mealApi: MealApi): MealSearchRepo {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealApi.getMealList(s)
    }
}