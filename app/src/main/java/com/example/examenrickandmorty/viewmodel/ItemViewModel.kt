package com.example.examenrickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenrickandmorty.data.ItemPerson
import com.example.examenrickandmorty.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val repository = ItemRepository()

    private val _items = MutableLiveData<List<ItemPerson>>()
    val items: LiveData<List<ItemPerson>> get() = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val itemsList = repository.getItems()
                _items.value = itemsList
            } catch (e: Exception) {
                _error.value = "Error al cargar los elementos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}