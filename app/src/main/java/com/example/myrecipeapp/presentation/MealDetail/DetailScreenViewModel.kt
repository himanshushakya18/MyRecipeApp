package com.example.myrecipeapp.presentation.MealDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.domain.use_case.GetMealsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCase: GetMealsDetailsUseCase
) : ViewModel() {
 private val _state = MutableStateFlow(DetailScreenState())
    val state  = _state.asStateFlow()

    fun getMealDetails(id:String){
        useCase.invoke(id).onEach {
            when (it){
                is Resource.Error -> {
                   _state.value.copy(
                       error = it.message?:""
                   )
                }
                is Resource.Loading ->{
                    _state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value.copy(
                        data = it.data
                    )
                }
            }
        }
    }
}