package com.example.examenrickandmorty.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.examenrickandmorty.domain.models.Character
import com.example.examenrickandmorty.domain.usecase.GetCharactersByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersByNameUseCase: GetCharactersByName
) : ViewModel() {
    private val _charactersFlow = MutableSharedFlow<PagingData<Character>>()
    val charactersFlow = _charactersFlow.asSharedFlow()

    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            is CharacterListEvent.GetAllCharactersByName -> onSearch(event.characterName)
        }
    }

    init {
        getCharactersByName("")
    }

    private fun getCharactersByName(characterName: String) {
        getCharactersByNameUseCase(characterName).onEach {
            _charactersFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getCharactersByName(query)
        }
    }
}