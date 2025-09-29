package com.example.examenrickandmorty.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.examenrickandmorty.data.cache.RickAndMortyDatabase
import com.example.examenrickandmorty.data.remote.RickAndMortyApi
import com.example.examenrickandmorty.data.remote.RickAndMortyApi.Companion.BASE_URL_API
import com.example.examenrickandmorty.data.repository.CharacterRepositoryImpl
import com.example.examenrickandmorty.domain.repository.CharacterRepository
import com.example.examenrickandmorty.domain.usecase.GetCharactersByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {
    @Singleton
    @Provides
    fun provideGetCharactersByNameUseCase(
        getCharactersRepository: CharacterRepository
    ): GetCharactersByName = GetCharactersByName(getCharactersRepository)

    @Singleton
    @Provides
    @ExperimentalPagingApi
    fun provideCharacterRepository(
        api: RickAndMortyApi,
        db: RickAndMortyDatabase
    ): CharacterRepository = CharacterRepositoryImpl(api, db)

    @Singleton
    @Provides
    fun provideRickAndMortyApi(
    ): RickAndMortyApi =
        Retrofit.Builder().baseUrl(BASE_URL_API).addConverterFactory(GsonConverterFactory.create())
            .build().create(RickAndMortyApi::class.java)

    @Singleton
    @Provides
    fun provideRickAndMortyDatabase(
        app: Application
    ): RickAndMortyDatabase =
        Room.databaseBuilder(app, RickAndMortyDatabase::class.java, "rickAndMortyDb").build()
}