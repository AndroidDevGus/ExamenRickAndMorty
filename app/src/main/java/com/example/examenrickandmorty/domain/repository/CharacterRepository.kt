package com.example.examenrickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.examenrickandmorty.domain.models.Character
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
    fun getCharactersByName(characterName: String): Flow<PagingData<Character>>
}