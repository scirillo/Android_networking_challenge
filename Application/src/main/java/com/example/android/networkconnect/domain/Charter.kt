package com.example.android.networkconnect.api.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Charter(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String
) : Parcelable
