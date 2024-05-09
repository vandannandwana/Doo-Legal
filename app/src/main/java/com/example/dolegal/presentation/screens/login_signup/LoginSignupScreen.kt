package com.example.dolegal.presentation.screens.login_signup

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.dolegal.R
import com.example.dolegal.presentation.viewmodel.HomeViewModel
import com.example.dolegal.presentation.models.TabItem
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginSignup(innerPadding: PaddingValues, context: Context,vModel:HomeViewModel) {

    val logintabItems = listOf(

        TabItem(
            title = "Login",
            selectedIcon = Icons.Filled.Key,
            unselectedIcon = Icons.Outlined.Key
        ),
        TabItem(
            title = "Signup",
            selectedIcon = Icons.Filled.Hub,
            unselectedIcon = Icons.Outlined.Hub
        )

    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        logintabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != selectedTabIndex) {
            selectedTabIndex = pagerState.currentPage
        }

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.loginback),
            contentDescription = "bg",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center
        )

        Column(modifier = Modifier.padding(innerPadding)) {

            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .padding(start = 90.dp, end = 90.dp, top = 20.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(60.dp)),
                indicator = { null }

            ) {

                logintabItems.forEachIndexed { index, item ->

                    Tab(modifier = Modifier.background(Color(0xFFDFD4C8)),
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = item.title) },
                        selectedContentColor = Color(0xFF365349),
                        unselectedContentColor = Color(0x50082C28),
                        icon = {
                            Icon(
                                imageVector = if (selectedTabIndex == index) {
                                    item.selectedIcon
                                } else item.unselectedIcon, modifier = Modifier.size(16.dp),
                                contentDescription = "home"
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { index ->

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (index == 0) {
                        LoginPage(context = context, vModel = vModel)
                    } else {
                        SignUpPage(context = context, vModel, onLoginClick = { selectedTabIndex = 0 })
                    }

                }

            }

        }
    }

}

