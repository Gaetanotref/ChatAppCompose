package com.example.learncompose.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.learncompose.ui.model.ChatItemModel

@Composable
fun RecyclerViewForCompose(listOfModel : List<ChatItemModel>){


    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(listOfModel){
            ChatItem(state = it)
        }
    }
}