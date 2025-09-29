package com.example.examenrickandmorty.domain.usecase

import androidx.paging.PagingData
import com.example.examenrickandmorty.domain.models.Character
import com.example.examenrickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersByName(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(characterName: String): Flow<PagingData<Character>> =
        characterRepository.getCharactersByName(characterName)
}