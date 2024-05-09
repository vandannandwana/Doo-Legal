package com.example.dolegal.presentation.models

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

data class BannerItem(
    val url: String
)

data class Users(
    val name:String,
    val email:String,
    val password:String,
){
    constructor() : this("", "", "")
}