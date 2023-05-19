package com.example.learncompose.ui.elements

import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learncompose.R
import com.example.learncompose.ui.model.UiModel

@Composable
fun FirstComposableFun(state: UiModel, modifier: Modifier = Modifier,bitmap: Bitmap?) {

    Row(modifier = Modifier.padding(all = 8.dp)) {

        if (bitmap != null) {
            bitmap.asImageBitmap().let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                        .border(
                            1.5.dp, MaterialTheme.colors.primary,
                            CircleShape
                        )
                )
            }
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
            Text(
                text = state.name,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
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
