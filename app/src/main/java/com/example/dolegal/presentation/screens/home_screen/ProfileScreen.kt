package com.example.dolegal.presentation.screens.home_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.dolegal.R
import com.example.dolegal.presentation.state.CurrentUserState
import com.example.dolegal.presentation.viewmodel.HomeViewModel

@Composable
fun ProfileScreen(
    innerpadding: PaddingValues,
    viewModel: HomeViewModel,
    onLogoutClick: () -> Unit,
    currentUserState: CurrentUserState
) {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }



    val photoPicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        imageUri = it
    }

    LazyColumn(modifier = Modifier
        .padding(innerpadding)
        .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

        item {

            AsyncImage(
                modifier = Modifier.clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                placeholder = painterResource(id = R.drawable.loading_placeholder),
                model = if(imageUri != null) imageUri else currentUserState.user?.profile_pic,
                contentDescription = "profile_pic"
            )

        }

        item {

            Button(onClick = { viewModel.updateProfile(uri = imageUri,currentUserState.user?.name) }) {

                Text("Change Profile")

            }

        }

        item {

            Button(onClick = { viewModel.logout(); onLogoutClick() }) {

                Text(text = imageUri.toString())

            }

        }


    }


}