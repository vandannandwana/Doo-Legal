package com.example.dolegal.presentation.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dolegal.R
import com.example.dolegal.presentation.models.Users
import com.example.dolegal.presentation.viewmodel.HomeViewModel

@Composable
fun PublicScreen(
    innerPadding: PaddingValues,
    viewModel: HomeViewModel,
    lifecycleOwner: LifecycleOwner
) {

    val usersState = viewModel.users.collectAsStateWithLifecycle(lifecycleOwner).value

    var usersList by remember {
            mutableStateOf<List<Users>?>(emptyList())
    }
    LaunchedEffect (usersState){
        usersList = usersState.users?.sortedBy { it.score.toInt() }?.reversed()
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            if (usersState.isLoading) {

                CircularProgressIndicator()

            } else if (usersState.errorMessage?.isEmpty() != true) {

                Text(text = usersState.errorMessage.toString())

            }

            if (usersState.users    ?.isNotEmpty() == true) {

                LazyColumn(modifier = Modifier.height(200.dp)) {

                    item {

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

                            Row {

                                Card(modifier = Modifier
                                    .size(200.dp)
                                    .padding(16.dp)
                                    .clip(CircleShape)
                                    .shadow(24.dp)){

                                    Image(modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop, painter = painterResource(id = R.drawable.c1), contentDescription = "pos1")

                                }

                                Card(modifier = Modifier
                                    .size(200.dp)
                                    .padding(16.dp)
                                    .clip(CircleShape)
                                    .shadow(24.dp)){

                                    Image(modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop, painter = painterResource(id = R.drawable.c2), contentDescription = "pos1")


                                }

                            }

                            Card(modifier = Modifier
                                .size(200.dp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .shadow(24.dp)){

                                Image(modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop, painter = painterResource(id = R.drawable.c3), contentDescription = "pos1")


                            }
                        }
                    }
                    item {
                        Box(modifier = Modifier.fillMaxWidth()){
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){

                                Image(painter = painterResource(id = R.drawable.second), contentDescription ="first", modifier = Modifier.size(54.dp))
                                Image(painter = painterResource(id = R.drawable.first), contentDescription ="first", modifier = Modifier.size(54.dp))
                                Image(painter = painterResource(id = R.drawable.third), contentDescription ="first", modifier = Modifier.size(54.dp))

                            }
                        }
                    }

                    items(usersList!!.size) { index ->

                        Leaderboard(usersList!![index])

                    }
                }

            }


        }


    }

}



@Composable
fun Leaderboard(user: Users) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(Color(0xB942665A)), contentAlignment = Alignment.Center){

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){

                Image(painter = painterResource(R.drawable.c4), contentScale = ContentScale.Crop, modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape), contentDescription = "")

                Text(text = user.name)
                Text(text = user.score)

            }

        }

    }

}