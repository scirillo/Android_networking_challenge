package com.example.android.networkconnect.api.response

import com.example.android.networkconnect.api.dto.CharacterDTO

class CharacterApiResponse(val info: InfoApiResponse, val results: List<CharacterDTO>)