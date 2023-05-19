package com.example.learncompose.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.elements.RecyclerViewForCompose
import com.example.learncompose.ui.model.ChatItemModel

@Composable
fun Chatscreen() {
    var list by remember {
        mutableStateOf(emptyList<ChatItemModel>())
    }
    var text by remember {
        mutableStateOf("")
    }
    var nome by remember {
        mutableStateOf("Utente")
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            imageUri = uri
            list = list.map { it.copy(uri = uri)}
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = {
                    launcher
                        .launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                },
                modifier = Modifier.padding(top = 8.dp).height(54.dp).clip(CircleShape)
            ) {
                Text(text = "Cambia immagine")
            }
            OutlinedTextField(
                modifier = Modifier.weight(0.4f),
                shape = CircleShape,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                },
                value = nome,
                onValueChange = { newName ->
                    nome = newName
                    list = list.map { it.copy(name = newName) }
                },
                label = { Text(text = "Nome") }
            )
        }
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Bottom
        ) {
            RecyclerViewForCompose(
                listOfModel = list
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                shape = CircleShape,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                },
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                label = { Text(text = "Messaggio") }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        val newModel = ChatItemModel(otherText = text, uri = imageUri, name = nome)
                        list += newModel
                        text = ""
                    }
                },
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        }
    }
}


