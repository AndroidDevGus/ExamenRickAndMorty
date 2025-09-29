package com.example.examenrickandmorty.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examenrickandmorty.domain.models.Character


@Entity(tableName = "tbCharacter")
data class CharacterEntity(
    val gender: String,
    @PrimaryKey val id: Int,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String
) {
    fun toCharacter() = Character(
        gender = gender,
        id = id,
        image = image,
        location = location,
        name = name,
        origin = origin,
        species = species,
        status = status
    )
}