package com.example.examenrickandmorty.repository

import com.example.examenrickandmorty.data.ItemPerson
import kotlinx.coroutines.delay

class ItemRepository {

    // Simula una llamada a API o base de datos
    suspend fun getItems(): List<ItemPerson> {
        // Simulamos delay de red
        delay(1000)

        return listOf(
            ItemPerson(1, "Elemento 1", "Descripción del primer elemento"),
            ItemPerson(2, "Elemento 2", "Descripción del segundo elemento"),
            ItemPerson(3, "Elemento 3", "Descripción del tercer elemento"),
            ItemPerson(4, "Elemento 4", "Descripción del cuarto elemento"),
            ItemPerson(5, "Elemento 5", "Descripción del quinto elemento"),
            ItemPerson(6, "Elemento 6", "Descripción del sexto elemento"),
            ItemPerson(7, "Elemento 7", "Descripción del séptimo elemento"),
            ItemPerson(8, "Elemento 8", "Descripción del octavo elemento"),
            ItemPerson(9, "Elemento 9", "Descripción del noveno elemento"),
            ItemPerson(10, "Elemento 10", "Descripción del décimo elemento")
        )
    }
}