package com.naisuapps.marveldataverse.data.repository

import com.naisuapps.marveldataverse.data.model.Character
import com.naisuapps.marveldataverse.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: CharacterService
) {
    suspend fun getAllCharacters(): List<Character> {
        val response = apiService.getCharacters()
        return response
    }
}