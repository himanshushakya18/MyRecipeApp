package com.example.myrecipeapp.presentation.MealSearch

import android.content.Context
import android.net.wifi.hotspot2.pps.HomeSp
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.R
import com.example.myrecipeapp.domain.model.Meal
import com.example.myrecipeapp.ui.theme.Background
import com.example.myrecipeapp.ui.theme.CardBg
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: MealSearchViewModel = getViewModel(),
    context: Context
) {

    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Background
                ),


                )
        }
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .background(Background)
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextField(
                    value = query,
                    onValueChange = {
                        if (it.all { it.isLetter() }) {
                            // If the new value is numeric, don't update the text
                            query = it
                        }

                        return@TextField
                    },

                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(50))
                        .height(60.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = CardBg,
                        cursorColor = Color.White,
                    ),
                    singleLine = true,

                    placeholder = {
                        Text(
                            text = "Search Recipe",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        color = Color.White,


                        ),

                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.getMealList(query) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Color.White
                            )
                        }
                    }


                )

            }
            if (state.error.isNotEmpty()) {

                Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
            }

            if (state.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()

                ) {

                    CircularProgressIndicator(
                        color = CardBg
                    )
                }
            }


            if (state.data?.isEmpty() == true) {


              Box(
                  modifier = Modifier.fillMaxSize(),
                  contentAlignment = Alignment.Center
              )  {
                  Text(
                      text = "Oh such a empty!",
                      color= Color.White,
                      style = MaterialTheme.typography.headlineSmall
                  )
              }


            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    items(items = state.data ?: emptyList()) {
                        ListItem(it) {
                            val id = it.mealId
                            navController.navigate("DETAILS/$id")
                        }
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
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBg
        )

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(80.dp)

            )
            Log.d("Image", meal.image)

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = meal.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,

                )
        }
    }
}
