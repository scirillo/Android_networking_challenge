package com.example.android.networkconnect

import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.ApiService
import com.example.android.networkconnect.api.dto.CharacterDTO
import com.example.android.networkconnect.api.response.CharacterApiResponse
import com.example.android.networkconnect.api.response.InfoApiResponse
import com.example.android.networkconnect.characterlist.CharacterRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test

class CharacterRepositoryTest {

    @Test
    fun getCharactersListSuccess() = runBlocking {
        class FakeApiService : ApiService {
            override suspend fun getCharacters(page: String): CharacterApiResponse {
                return CharacterApiResponse(
                    info = InfoApiResponse(
                        500, 42,
                        "https://rickandmortyapi.com/api/character?page=3",
                        "https://rickandmortyapi.com/api/character?page=1"
                    ),
                    results = listOf(
                        CharacterDTO(
                            1, "Aqua Morty",
                            "unknown",
                            "Humanoid",
                            "Fish-Person",
                            "Male", "https://rickandmortyapi.com/api/character/avatar/21.jpeg",
                        ),
                        CharacterDTO(
                            22, "Arcade Alien",
                            "unknown",
                            "Alien",
                            "",
                            "Male", "https://rickandmortyapi.com/api/character/avatar/23.jpeg",
                        )
                    )
                )
            }
        }

        val characterRepository = CharacterRepository(
            apiService = FakeApiService(),
            dispatcher = TestCoroutineDispatcher()
        )

        val apiResponseStatus = characterRepository.getCharacters("1")
        assert(apiResponseStatus is ApiResponseStatus.Success)
        val characterList = (apiResponseStatus as ApiResponseStatus.Success).data
        assertEquals(2, characterList.size)
        assertEquals("Aqua Morty", characterList[1].name)
        assertEquals("Arcade Alien", characterList[0].name)
    }

    @Test
    fun getCharactersListFailure() = runBlocking {
        class FakeApiService : ApiService {
            override suspend fun getCharacters(page: String): CharacterApiResponse {
                return CharacterApiResponse(
                    info = InfoApiResponse(
                        500, 42,
                        "https://rickandmortyapi.com/api/character?page=3",
                        "https://rickandmortyapi.com/api/character?page=1"
                    ),
                    results = listOf(
                        CharacterDTO(
                            1, "Aqua Morty",
                            "unknown",
                            "Humanoid",
                            "Fish-Person",
                            "Male", "https://rickandmortyapi.com/api/character/avatar/21.jpeg",
                        )
                    )
                )
            }
        }

        val characterRepository = CharacterRepository(
            apiService = FakeApiService(),
            dispatcher = TestCoroutineDispatcher()
        )

        val apiResponseStatus = characterRepository.getCharacters("1")
        assert(apiResponseStatus is ApiResponseStatus.Success)
        assert(apiResponseStatus is ApiResponseStatus.Error)
        assertEquals(R.string.api_error_message,
            (apiResponseStatus as ApiResponseStatus.Error).errorId)
    }
}