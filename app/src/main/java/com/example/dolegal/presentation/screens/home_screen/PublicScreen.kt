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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(
                text = "LeaderBoard",
                fontWeight = FontWeight.Normal,
                color = Color(0xE8272727),
                fontSize = 37.sp,
                fontFamily = FontFamily(Font(R.font.story_categoryy))
            )
        }

        item {

            if (usersState.isLoading) {

                CircularProgressIndicator()

            } else if (usersState.errorMessage?.isEmpty() != true) {

                Text(text = usersState.errorMessage.toString())

            }

        }

        if (!usersState.users.isNullOrEmpty()) {

            if(usersState.users.size>=2){


            item {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    Row {

                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .shadow(24.dp)
                        ) {

                            AsyncImage(
                                placeholder = painterResource(id = R.drawable.loading_placeholder),
                                modifier = Modifier.size(200.dp),
                                contentScale = ContentScale.Crop,
                                model = usersState.users.sortedByDescending { it.score.toInt() }[usersState.users.size -2].profile_pic,
                                contentDescription = "pos1"
                            )

                        }

                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .shadow(24.dp)
                        ) {

                            AsyncImage(
                                placeholder = painterResource(id = R.drawable.loading_placeholder),
                                modifier = Modifier.size(200.dp),
                                contentScale = ContentScale.Crop,
                                model = usersState.users.sortedByDescending { it.score.toInt() }[usersState.users.size -1].profile_pic,
                                contentDescription = "pos1"
                            )


                        }

                    }

                    Card(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                            .clip(CircleShape)
                            .shadow(24.dp)
                    ) {

                        AsyncImage(
                            placeholder = painterResource(id = R.drawable.loading_placeholder),
                            modifier = Modifier.size(200.dp),
                            contentScale = ContentScale.Crop,
                            model = usersState.users.sortedByDescending { it.score.toInt() }[usersState.users.size -3].profile_pic,
                            contentDescription = "pos1"
                        )


                    }
                }
            }
                }
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.second),
                            contentDescription = "first",
                            modifier = Modifier.size(54.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.first),
                            contentDescription = "first",
                            modifier = Modifier.size(54.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.third),
                            contentDescription = "first",
                            modifier = Modifier.size(54.dp)
                        )

                    }
                }
            }



            items(usersState.users.sortedByDescending { it.score.toInt() }) { user ->
                Leaderboard(user = user)
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color(0xB942665A)), contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    placeholder = painterResource(id = R.drawable.loading_placeholder),
                    model = user.profile_pic,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(34.dp)
                        .clip(CircleShape),
                    contentDescription = ""
                )

                Text(text = user.name)
                Text(text = user.score, modifier = Modifier.padding(8.dp))

            }

        }

    }

}