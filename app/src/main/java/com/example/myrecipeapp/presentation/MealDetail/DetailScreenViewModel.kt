package com.example.myrecipeapp.presentation.MealDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myrecipeapp.common.Resource
import com.example.myrecipeapp.domain.use_case.GetMealsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCase: GetMealsDetailsUseCase
) : ViewModel() {
 var state by mutableStateOf(DetailScreenState())
     private set
    fun getMealDetails(id:String){
        useCase.invoke(id).onEach {
            when (it){
                is Resource.Error -> {
                   state = state.copy(
                       error = (it.message.toString())
                   )
                }
                is Resource.Loading ->{
                    state = state.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        data = it.data
                    )
                }
            }
        }
    }
}