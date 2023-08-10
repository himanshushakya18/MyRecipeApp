package com.example.myrecipeapp.data.remote

import com.example.myrecipeapp.common.Constants
import com.example.myrecipeapp.data.model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET(Constants.SEARCH_ENDPOINT)
    suspend fun getMealList(@Query("s") s:String): MealsDTO
    @GET(Constants.DETAIL_ENDPOINT)
    suspend fun getMealDetails(@Query("i")i:String): MealsDTO
}