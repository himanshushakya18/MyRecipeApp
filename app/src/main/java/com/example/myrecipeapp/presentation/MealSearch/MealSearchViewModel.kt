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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class MealSearchViewModel (private val getMealUseCase: GetMealsUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(MealSearchState())
    var state = _state.asStateFlow()

    fun getMealList(s: String) {
        getMealUseCase.invoke(s).onEach {
            when (it) {
                is Resource.Error -> {
                    _state.value = MealSearchState(error = it.message?:"")
                }

                is Resource.Loading -> {
                    _state.value= MealSearchState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}