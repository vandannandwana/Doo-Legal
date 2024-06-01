package com.example.dolegal.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dolegal.presentation.viewmodel.HomeViewModel

@Composable
fun ProfileScreen(innerpadding: PaddingValues, viewModel: HomeViewModel, onLogoutClick: () -> Unit) {
    LazyColumn(modifier = Modifier.padding(innerpadding).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

        item {

            Button(onClick = { viewModel.logout(); onLogoutClick() }) {

                Text(text = "Logout")

            }

        }


    }


}