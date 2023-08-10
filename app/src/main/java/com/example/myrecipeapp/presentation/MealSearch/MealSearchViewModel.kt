package com.example.myrecipeapp.presentation.MealSearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.domain.use_case.GetMealsUseCase
import com.example.myrecipeapp.presentation.MealSearch.MealSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealUseCase: GetMealsUseCase) :
    ViewModel() {
    var _state by mutableStateOf(MealSearchState())
        private set

    fun getMealList(s: String) {
        getMealUseCase.invoke(s).onEach {
            when (it) {
                is Resource.Error -> {
                    _state = MealSearchState(error = it.message ?: "")
                }

                is Resource.Loading -> {
                    _state= MealSearchState(isLoading = true)
                }

                is Resource.Success -> {
                    _state = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}