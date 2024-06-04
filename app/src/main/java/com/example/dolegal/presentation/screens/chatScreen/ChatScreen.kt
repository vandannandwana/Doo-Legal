package com.example.dolegal.presentation.screens.chatScreen

import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.channels.ChannelListViewModel
import io.getstream.chat.android.compose.viewmodel.channels.ChannelViewModelFactory
import io.getstream.chat.android.models.Filters
import io.getstream.chat.android.models.querysort.QuerySortByField

class ChatScreen : ComponentActivity() {

    private val factory by lazy {
        ChannelViewModelFactory(
            chatClient = ChatClient.instance(),
            querySort = QuerySortByField.descByName("last_updated"),
            filters = Filters.`in`(
                fieldName = "type",
                values = listOf("messaging","team","gaming")
            )
        )
    }

    private val listViewModel:ChannelListViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ChatTheme{
                ChannelsScreen(
                    viewModelFactory = factory,
                    title = "Channel List",
                    isShowingSearch = true,
                    onBackPressed = { finish() }
                )
            }
        }

    }

}