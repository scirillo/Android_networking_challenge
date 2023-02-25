package com.example.android.networkconnect.api.dto

import com.squareup.moshi.Json

class CharacterDTO(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type  : String,
    val gender  : String,
    @field:Json(name = "image") val imageUrl: String
)
