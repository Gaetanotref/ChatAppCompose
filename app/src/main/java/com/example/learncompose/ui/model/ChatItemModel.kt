package com.example.learncompose.ui.model

import android.net.Uri

data class ChatItemModel(
    val name: String? = "Utente",
    val otherText : String,
    val uri: Uri?
    )
