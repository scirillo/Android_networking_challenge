package com.example.android.networkconnect.api.dto

import com.example.android.networkconnect.api.domain.Charter

class CharacterDTOMapper {

    private fun fromCharacterDTOToCharacterDomain(characterDTO: CharacterDTO): Charter {
        return Charter(
            characterDTO.id,
            characterDTO.name,
            characterDTO.status,
            characterDTO.species,
            characterDTO.type,
            characterDTO.gender,
            characterDTO.imageUrl
        )
    }

    fun formCharacterDTOListToCharacterDomainList(characterDtoList: List<CharacterDTO>): List<Charter> {
        return characterDtoList.map {
            fromCharacterDTOToCharacterDomain(it)
        }
    }
}