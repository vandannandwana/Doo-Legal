package com.example.dolegal.presentation.screens.home_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dolegal.R
import com.example.dolegal.presentation.models.BannerItem
import com.example.dolegal.presentation.models.Users

@Composable
fun MainScreen(innerPadding: PaddingValues = PaddingValues(16.dp), context: Context) {

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),horizontalArrangement = Arrangement.SpaceBetween){

                Image(
                    painter = painterResource(R.drawable.mylogo),
                    contentDescription = "mylogo",
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .size(34.dp)

                )
                Image(
                    painter = painterResource(R.drawable.signupback),
                    contentDescription = "mylogo",
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .size(64.dp)
                )

            }

            Text(text = " Hello Back,\n Vandan Nandwana", modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(12.dp))

            BannerList()

        }

}
@Preview(showSystemUi = true)
@Composable
fun BannerList() {

    val banners = listOf(
        BannerItem(
            "https://static.vecteezy.com/system/resources/previews/000/414/335/original/vector-banner-template-with-four-happy-kids.jpg"
        ),
        BannerItem(
            "https://media.istockphoto.com/id/487583768/vector/cartoon-little-kid-reading-book.jpg?s=612x612&w=0&k=20&c=xo928M8NshfIWcTnD5Ywva7FuCGpsLDESyY51QOyIrA="
        ),
        BannerItem(
            "https://th.bing.com/th/id/OIP.XmfAJsz2W0a8i-3_lKSt6AAAAA?rs=1&pid=ImgDetMain"
        ),
        BannerItem(
            "https://image.shutterstock.com/image-vector/cartoon-kids-reading-book-260nw-1171462306.jpg"
        )
    )


    LazyRow{
        items(banners){item->

            Banner(url = item.url)

        }
    }

}

@Composable
fun Banner(url: String) {
    Box(modifier = Modifier
        .padding(24.dp)
        .height(290.dp)
        .width(250.dp)
        .clip(RoundedCornerShape(20.dp))) {
        AsyncImage(
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

