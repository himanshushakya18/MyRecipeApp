package com.example.myrecipeapp.presentation.MealDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.domain.use_case.GetMealsDetailsUseCase
import com.example.myrecipeapp.presentation.MealSearch.MealSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class DetailScreenViewModel (
    private val useCase: GetMealsDetailsUseCase
) : ViewModel() {
 private val _state = MutableStateFlow(DetailScreenState())
    val state  = _state.asStateFlow()

    fun getMealDetails(id:String){
        useCase.invoke(id).onEach {
             when(it){
                is Resource.Error -> {
                    _state.value = DetailScreenState(error = it.message?:"")
                }
                is Resource.Loading ->{
                    _state.value= DetailScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = DetailScreenState(data = it.data)
                }



             }
        }.launchIn(viewModelScope)
    }
}