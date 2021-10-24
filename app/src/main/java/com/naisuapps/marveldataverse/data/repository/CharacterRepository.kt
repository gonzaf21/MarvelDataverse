package com.naisuapps.marveldataverse.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.model.comics.Comic
import com.naisuapps.marveldataverse.data.network.CharacterService
import com.naisuapps.marveldataverse.data.paging.CharacterPagingSource
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: CharacterService
) {
    fun getAllCharacters(): Flow<PagingData<Character>> {
        Logger.d("$TAG -> New page")
        return Pager(
            config = PagingConfig(
                pageSize = CURRENT_LIMIT_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    suspend fun getCharacterComics(characterId: Int): List<Comic> {
        val response = apiService.getCharacterComics(characterId)
        return response
    }

    suspend fun getRandomCharacter(): List<Character> {
        val response = apiService.getRandomCharacter()
        return response
    }

    companion object {
        private val TAG = this::class.java
        private const val CURRENT_LIMIT_SIZE = 20
    }
}