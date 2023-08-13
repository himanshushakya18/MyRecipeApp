package com.example.myrecipeapp.presentation.MealDetail


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.Screens
import com.example.myrecipeapp.ui.theme.Background
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = getViewModel(),
    id: String,
    context: Context,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMealDetails(id)
        Log.d("DetailScreen", "data fetched")
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
               colors = TopAppBarDefaults.smallTopAppBarColors(
                   containerColor = Background
               ) ,
                title = {

                    MyText(
                        text = state.data?.name ?: "",
                        style = MaterialTheme.typography.headlineMedium
                    )

                },
                navigationIcon = {
                    IconButton (onClick = {
                        navController.navigate(Screens.DETAILS.name){
                            popUpTo(Screens.DETAILS.name){
                                inclusive = true
                            }
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "arrow icon",
                            tint = Color.White
                        )
                    }
                }

            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Background)
        ) {
            item {
                if (state.error.isNotEmpty()) {

                    Toast.makeText(context,state.error, Toast.LENGTH_LONG).show()
                }

                Image(
                    painter = rememberAsyncImagePainter(state.data?.image),
                    contentDescription = "Image of Dish",
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .aspectRatio(1f)

                )
                MyText(
                    style = MaterialTheme.typography.headlineMedium,
                    text = "Instructions:"
                )
                MyText(
                    style = MaterialTheme.typography.bodySmall,
                    text = state.data?.instructions ?: ""
                )
                MyText(
                    style = MaterialTheme.typography.headlineMedium,
                    text = "Ingredients:"
                )
                Ingredient(
                    ingredient = state.data?.ingredient1 ?: "",
                    measurement = state.data?.measure1 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient2 ?: "",
                    measurement = state.data?.measure2 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient3 ?: "",
                    measurement = state.data?.measure3 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient4 ?: "",
                    measurement = state.data?.measure4 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient5 ?: "",
                    measurement = state.data?.measure5 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient6 ?: "",
                    measurement = state.data?.measure6 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient7 ?: "",
                    measurement = state.data?.measure7 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient8 ?: "",
                    measurement = state.data?.measure8 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient9 ?: "",
                    measurement = state.data?.measure9 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient10 ?: "",
                    measurement = state.data?.measure10 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient11 ?: "",
                    measurement = state.data?.measure11 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient12 ?: "",
                    measurement = state.data?.measure12 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient13 ?: "",
                    measurement = state.data?.measure13 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient14 ?: "",
                    measurement = state.data?.measure14 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient15 ?: "",
                    measurement = state.data?.measure15 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient16 ?: "",
                    measurement = state.data?.measure16 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient17 ?: "",
                    measurement = state.data?.measure17 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient18 ?: "",
                    measurement = state.data?.measure18 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient19 ?: "",
                    measurement = state.data?.measure19 ?: ""
                )
                Ingredient(
                    ingredient = state.data?.ingredient20 ?: "",
                    measurement = state.data?.measure20 ?: ""
                )


            }
        }
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
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Text(
            text = measurement,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
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
            .padding(4.dp),
        style = style,
        color = Color.White
    )
}