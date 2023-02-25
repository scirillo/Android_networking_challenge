package com.example.android.networkconnect.characterlist

import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.ApiService
import com.example.android.networkconnect.api.Util
import com.example.android.networkconnect.api.domain.Charter
import com.example.android.networkconnect.api.dto.CharacterDTOMapper
import com.example.android.networkconnect.api.response.InfoApiResponse
import com.example.android.networkconnect.makeServiceCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterTasks {
    suspend fun getCharacters(page: String = "1"): ApiResponseStatus<List<Charter>>
}

class CharacterRepository @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : CharacterTasks {
    override suspend fun getCharacters(page: String): ApiResponseStatus<List<Charter>> {
        return withContext(dispatcher) {
            getCharacter(page)
        }
    }

    private suspend fun getCharacter(page: String): ApiResponseStatus<List<Charter>> =
        makeServiceCall {
            val characterListApiResponse = apiService.getCharacters(page)
            val characterDTOList = characterListApiResponse.results
            val characterDTOMapper = CharacterDTOMapper()
            savePage(characterListApiResponse.info)
            characterDTOMapper.formCharacterDTOListToCharacterDomainList(characterDTOList)
        }

    private fun savePage(info: InfoApiResponse) {
        Util.savePages(info)
    }
}