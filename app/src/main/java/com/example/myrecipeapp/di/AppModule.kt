package com.example.myrecipeapp.di

import com.example.myrecipeapp.common.Constants
import com.example.myrecipeapp.data.remote.MealApi
import com.example.myrecipeapp.data.repository.MealDetailsRepoImpl
import com.example.myrecipeapp.data.repository.MealSearchRepoImpl
import com.example.myrecipeapp.domain.repository.MealDetailsRepo
import com.example.myrecipeapp.domain.repository.MealSearchRepo
import com.example.myrecipeapp.domain.use_case.GetMealsDetailsUseCase
import com.example.myrecipeapp.domain.use_case.GetMealsUseCase
import com.example.myrecipeapp.presentation.MealDetail.DetailScreenViewModel
import com.example.myrecipeapp.presentation.MealSearch.MealSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{
    single{
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }
    single<MealSearchRepo>{
        MealSearchRepoImpl(get())
    }
    single{
        GetMealsUseCase(get())
    }
    viewModel{
        MealSearchViewModel(get())
    }
    single<MealDetailsRepo> {
        MealDetailsRepoImpl(get())
    }
    single {
        GetMealsDetailsUseCase(get())
    }

    viewModel{
        DetailScreenViewModel(get())
    }
}