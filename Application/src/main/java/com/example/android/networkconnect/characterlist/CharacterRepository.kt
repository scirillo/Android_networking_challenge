package com.example.android.networkconnect.characterlist

import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.RickAndMortyApi
import com.example.android.networkconnect.api.Util
import com.example.android.networkconnect.api.domain.Charter
import com.example.android.networkconnect.api.dto.CharacterDTOMapper
import com.example.android.networkconnect.api.response.InfoApiResponse
import com.example.android.networkconnect.makeServiceCall

class CharacterRepository {

     suspend fun getCharacters(page: String = "1"): ApiResponseStatus<List<Charter>> = makeServiceCall {
        val characterListApiResponse = RickAndMortyApi.retrofitService.getCharacters(page)
        val characterDTOList = characterListApiResponse.results
        val characterDTOMapper = CharacterDTOMapper()
        savePage(characterListApiResponse.info)
        characterDTOMapper.formCharacterDTOListToCharacterDomainList(characterDTOList)
    }

    private fun savePage(info: InfoApiResponse) {
        Util.savePages(info)
    }
}