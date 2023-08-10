package com.example.myrecipeapp.presentation.MealSearch

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.Screens
import com.example.myrecipeapp.domain.model.Meal
import com.example.myrecipeapp.ui.theme.Background
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: MealSearchViewModel = viewModel()
) {

    val state = viewModel._state
    var query by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                },

                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(50)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.getMealList(query) },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }


            )

        }
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }


        if (state.data != null) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(items = state.data) {
                    ListItem(it) {
                        val id = it.mealId
                        navController.navigate(Screens.DETAILS.name + "$id")
                    }
                }
            }
        }


    }
    LaunchedEffect(key1 = query) {
        delay(1000)
        if (query.isNotBlank()) {
            viewModel.getMealList(query)
        }
    }
}

@Composable
fun ListItem(meal: Meal, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            painter = rememberAsyncImagePainter(meal.image),
            contentDescription = "Image of Dish",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(80.dp)

        )
        Log.d("Image", "${meal.image}")

        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = meal.name,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}