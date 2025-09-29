package com.example.examenrickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.examenrickandmorty.data.cache.RickAndMortyDatabase
import com.example.examenrickandmorty.data.paging.CharacterByNameRemoteMediator
import com.example.examenrickandmorty.data.remote.RickAndMortyApi
import com.example.examenrickandmorty.domain.models.Character
import com.example.examenrickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val db: RickAndMortyDatabase
) : CharacterRepository {
    override fun getCharactersByName(characterName: String): Flow<PagingData<Character>> {
        val pagingSourceFactory = { db.characterDao.getCharactersByName(characterName) }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = CharacterByNameRemoteMediator(
                api,
                db,
                characterName
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { CharacterEntityPagingData ->
            CharacterEntityPagingData.map { characterEntity -> characterEntity.toCharacter() }
        }
    }
}