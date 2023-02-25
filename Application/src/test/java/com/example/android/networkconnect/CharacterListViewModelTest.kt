package com.example.android.networkconnect

import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.domain.Charter
import com.example.android.networkconnect.characterlist.CharacterListViewModel
import com.example.android.networkconnect.characterlist.CharacterTasks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class CharacterListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun downloadCharacterListCorrect() {
        class FakeRepository : CharacterTasks {
            override suspend fun getCharacters(page: String): ApiResponseStatus<List<Charter>> {
             return ApiResponseStatus.Success(
                    listOf(
                        Charter(
                            1, "Aqua Morty",
                            "unknown",
                            "Humanoid",
                            "Fish-Person",
                            "Male", "https://rickandmortyapi.com/api/character/avatar/21.jpeg",
                        ),
                        Charter(
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
        val viewModel = CharacterListViewModel(
            FakeRepository()
        )
        assertEquals(2, viewModel.characterList.value?.size)
        viewModel.characterList.value?.get(1)?.let { assertEquals(22, it.id) }

        assert(viewModel.status.value is ApiResponseStatus.Success)
    }

    @Test
    fun downloadCharacterStatusCorrect() {
        class FakeRepository : CharacterTasks {
            override suspend fun getCharacters(page: String): ApiResponseStatus<List<Charter>> {
                return ApiResponseStatus.Error(R.string.connection_error)
            }
        }
        val viewModel = CharacterListViewModel(
            FakeRepository()
        )
        assertEquals(0, viewModel.characterList.value?.size)

        assert(viewModel.status.value is ApiResponseStatus.Error)
    }
}