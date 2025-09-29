package com.example.examenrickandmorty.domain.models

data class RemoteKey(
    val id: Int,
    val nextKey: Int,
    val isEndReached: Boolean
)