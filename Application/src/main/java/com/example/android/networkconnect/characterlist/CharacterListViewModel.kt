package com.example.android.networkconnect.characterlist


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.networkconnect.api.ApiResponseStatus
import com.example.android.networkconnect.api.domain.Charter
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {
    var characterList = MutableLiveData<List<Charter>>(listOf())
        private set

    var status = MutableLiveData<ApiResponseStatus<Any>?>(null)
        private set


    private val characterRepository = CharacterRepository()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(characterRepository.getCharacters())
        }
    }

    fun getCharacters(page: String) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(characterRepository.getCharacters(page))
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Charter>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            characterList.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }
}