package com.example.myrecipeapp.domain.use_case

import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.data.model.toDomainMeal
import com.example.myrecipeapp.data.repository.MealSearchRepoImpl
import com.example.myrecipeapp.domain.model.Meal
import com.example.myrecipeapp.domain.repository.MealSearchRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealsUseCase (private val repository: MealSearchRepo) {
    operator fun invoke(s:String):Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getMealList(s)
            val list = if(response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map { it.toDomainMeal() }
            emit(Resource.Success(data = list))
        }catch (e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))
        }
        catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Please check your internet connection"))
        }
    }
}