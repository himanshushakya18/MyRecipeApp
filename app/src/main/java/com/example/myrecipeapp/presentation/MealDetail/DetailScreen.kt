package com.example.myrecipeapp.presentation.MealDetail

import android.icu.text.UnicodeSetIterator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = viewModel(),
    id: String
) {
    var state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.getMealDetails(id)
    }
    if(state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if(state.error.isNotEmpty()){
            Text(
                text = state.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Image(
            painter = rememberAsyncImagePainter(state.data?.image),
            contentDescription = "image of dish",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
        )
        MyText(
            style = MaterialTheme.typography.headlineLarge,
            text = state.data?.name ?: ""
        )
        MyText(
            style = MaterialTheme.typography.headlineMedium,
            text = "Instructions"
        )
        MyText(
            style = MaterialTheme.typography.bodyMedium,
            text = state.data?.instructions ?: ""
        )
        MyText(
            style = MaterialTheme.typography.headlineMedium,
            text = "ingredients"
        )
        Ingredient(
            ingredient = state.data?.ingredient1 ?: "",
            measurement = state.data?.ingredient1 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient2 ?: "",
            measurement = state.data?.ingredient2 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient3 ?: "",
            measurement = state.data?.ingredient3 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient4 ?: "",
            measurement = state.data?.ingredient4 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient5 ?: "",
            measurement = state.data?.ingredient5 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient6 ?: "",
            measurement = state.data?.ingredient6 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient7 ?: "",
            measurement = state.data?.ingredient7 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient8 ?: "",
            measurement = state.data?.ingredient8 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient9 ?: "",
            measurement = state.data?.ingredient9 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient10 ?: "",
            measurement = state.data?.ingredient10 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient11 ?: "",
            measurement = state.data?.ingredient11 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient12 ?: "",
            measurement = state.data?.ingredient12 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient13 ?: "",
            measurement = state.data?.ingredient14 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient15 ?: "",
            measurement = state.data?.ingredient15 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient16 ?: "",
            measurement = state.data?.ingredient16 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient17 ?: "",
            measurement = state.data?.ingredient17 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient18 ?: "",
            measurement = state.data?.ingredient18 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient19 ?: "",
            measurement = state.data?.ingredient19 ?: ""
        )
        Ingredient(
            ingredient = state.data?.ingredient20 ?: "",
            measurement = state.data?.ingredient20 ?: ""
        )


    }
}

@Composable
fun Ingredient(
    ingredient: String,
    measurement: String

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = measurement,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MyText(
    style: androidx.compose.ui.text.TextStyle,
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        style = style
    )
}