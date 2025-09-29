package com.example.examenrickandmorty.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenrickandmorty.data.cache.dao.CharacterDao
import com.example.examenrickandmorty.data.cache.dao.RemoteKeyDao
import com.example.examenrickandmorty.data.cache.entity.CharacterEntity
import com.example.examenrickandmorty.data.cache.entity.RemoteKeyEntity

@Database(entities = [CharacterEntity::class, RemoteKeyEntity::class], version = 1)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val remoteKeyDao: RemoteKeyDao
}