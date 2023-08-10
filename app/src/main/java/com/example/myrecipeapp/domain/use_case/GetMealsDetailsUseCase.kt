package com.example.myrecipeapp.domain.use_case

import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.data.model.toDomainMealDetail
import com.example.myrecipeapp.domain.model.MealDetails
import com.example.myrecipeapp.domain.repository.MealDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealsDetailsUseCase @Inject constructor(private val repository: MealDetailsRepo) {
    operator fun invoke(id:String):Flow<Resource<MealDetails>> = flow {
            try {
                emit(Resource.Loading())
                val response = repository.getMealDetails(id).meals[0].toDomainMealDetail()
                emit(Resource.Success(data = response))

            }catch (e:HttpException){
                emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))
            }
            catch (e:IOException){
                emit(Resource.Error(message = e.localizedMessage?:"Please check your internet connection"))
            }
        }
    }
