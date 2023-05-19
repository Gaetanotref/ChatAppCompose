package com.example.learncompose.ui.elements

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.learncompose.ui.model.UiModel

@Composable
fun RecyclerViewForCompose(listOfModel : List<UiModel>){


    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(listOfModel){
            FirstComposableFun(state = it)
        }
    }
}