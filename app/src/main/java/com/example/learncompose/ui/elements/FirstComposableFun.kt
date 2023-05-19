package com.example.learncompose.ui.elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.learncompose.R
import com.example.learncompose.ui.model.UiModel

@Composable
fun FirstComposableFun(state: UiModel, modifier: Modifier = Modifier) {

    Row(modifier = Modifier.padding(all = 8.dp)) {

        if (state.uri != null) {
            AsyncImage(
                model = state.uri,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        1.5.dp, MaterialTheme.colors.primary,
                        CircleShape
                    ).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                modifier = modifier
                    .size(60.dp)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape),
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.this_is_a_description)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface) {
        }
        Column() {
            state.name?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = surfaceColor,
                elevation = 8.dp,
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .animateContentSize()
                    .padding(1.dp)) {

                Text(
                    text = state.otherText,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}
