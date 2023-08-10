package com.example.myrecipeapp.hilt

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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {
    @Provides
    @Singleton
    fun providesApi(): MealApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }

    @Provides
    fun providesMealSearchRepo(mealApi: MealApi): MealSearchRepo {
        return MealSearchRepoImpl(mealApi)
    }

    @Provides
    fun providesMealDetailsRepo(mealApi: MealApi): MealDetailsRepo {
        return MealDetailsRepoImpl(mealApi)
    }

    @Provides
    fun providesGetMealUseCase(repoImpl: MealSearchRepoImpl): GetMealsUseCase {
        return GetMealsUseCase(repoImpl)
    }

    @Provides
    fun providesMealSearchViewModel(useCase: GetMealsUseCase): MealSearchViewModel {
        return MealSearchViewModel(useCase)
    }

    @Provides
    fun providesGetMealDetailsUseCase(repoImpl: MealDetailsRepoImpl): GetMealsDetailsUseCase {
        return GetMealsDetailsUseCase(repoImpl)
    }

    @Provides
    fun provideDetailScreenViewModel(useCase: GetMealsDetailsUseCase): DetailScreenViewModel {
        return DetailScreenViewModel(useCase)
    }
}