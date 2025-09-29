package com.example.examenrickandmorty.presentation.character_list

sealed class CharacterListEvent {
    data class GetAllCharactersByName(val characterName: String) : CharacterListEvent()
}