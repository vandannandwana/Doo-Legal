package com.example.dolegal.presentation.screens.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.dolegal.R
import com.example.dolegal.presentation.models.BottomNavigationItems
import com.example.dolegal.presentation.screens.home_screen.coponents.StoryCategories
import com.example.dolegal.presentation.state.StoryCategoryState
import com.example.dolegal.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    innerPadding: PaddingValues = PaddingValues(16.dp),
    viewModel: HomeViewModel,
    onLogoutClick: () -> Unit,
    lifecycleOwner: LifecycleOwner,
    onCategoryClick: (category: String) -> Unit
) {
    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        3
    }

    LaunchedEffect(selectedItemIndex) {
        pagerState.scrollToPage(selectedItemIndex)
    }
    LaunchedEffect(pagerState) {
        while (pagerState.currentPage != selectedItemIndex) {
            selectedItemIndex = pagerState.currentPage
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(onClick = { selectedItemIndex = it }, pagerState.currentPage)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF00A5E3)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            HorizontalPager(state = pagerState) { index ->
                when (index) {

                    1 -> PublicScreen(innerPadding = it, viewModel, lifecycleOwner)
                    2 -> ProfileScreen(innerpadding = it, viewModel, onLogoutClick)
                    else -> HomeScreen(
                        lifecycleOwner,
                        innerPadding,
                        viewModel,
                        onCategoryClick
                    )

                }

            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner,
    innerPadding: PaddingValues = PaddingValues(16.dp),
    viewModel: HomeViewModel,
    onCategoryClick: (category: String) -> Unit
) {
    val storyBanners = viewModel.banners.collectAsStateWithLifecycle(lifecycleOwner).value

    val currentUserState = viewModel.currentUser.collectAsStateWithLifecycle(lifecycleOwner).value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.do_legal_logo_notext),
                    contentDescription = "myLogo",
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(CircleShape)
                        .size(64.dp)
                )
                if (currentUserState.user != null) {
                    Row {
                        Text(
                            text = "Hello ",
                            fontWeight = FontWeight.Medium,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontFamily = FontFamily(Font(R.font.story_categoryy))
                        )

                        Text(
                            text = currentUserState.user.name,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontFamily = FontFamily(Font(R.font.story_categoryy))
                        )

                    }


                    AsyncImage(
                        model = currentUserState.user.profile_pic,
                        contentDescription = "myLogo",
                        modifier = Modifier
                            .padding(14.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .size(64.dp)
                    )
                }

            }

        }

        item {
            BannerList(storyBanners)

        }

        item {

            StoryCategories(onCategoryClick)

        }

    }

}

@Composable
fun BannerList(storyCategories: StoryCategoryState) {

    if (storyCategories.isLoading) {
        CircularProgressIndicator()
    } else if (storyCategories.errorMessage?.isEmpty() != true) {
        Text(text = storyCategories.errorMessage.toString())
    }
    if (storyCategories.categories?.isNotEmpty() == true) {
        LazyRow {
            items(storyCategories.categories) { item ->

                Banner(url = item.image_url)

            }
        }

    }


}

@Composable
fun Banner(url: String) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .height(290.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        AsyncImage(
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavigation(onClick: (index: Int) -> Unit, pagerState: Int) {
    val navigationItems = listOf(
        BottomNavigationItems(
            title = "home",
            selectedIcon = R.drawable.story_filled,
            unselectedIcon = R.drawable.story_outlined
        ),
        BottomNavigationItems(
            title = "public",
            selectedIcon = R.drawable.leaderboard_filled,
            unselectedIcon = R.drawable.leaderboard_outlined
        ),
        BottomNavigationItems(
            title = "profile",
            selectedIcon = R.drawable.profile_filled,
            unselectedIcon = R.drawable.profile_outlined
        )
    )

    NavigationBar(containerColor = Color(0xB942665A)) {
        navigationItems.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = pagerState == index,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFFF96C5),
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Black
                ),
                onClick = { onClick(index) },
                label = { Text(item.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = if (pagerState == index) item.selectedIcon else item.unselectedIcon),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp),
                        contentDescription = "bottom"
                    )
                })

        }
    }


}

