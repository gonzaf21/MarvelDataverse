package com.naisuapps.marveldataverse.data.network

import com.naisuapps.marveldataverse.data.model.characters.BaseCharacterResponse
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.model.comics.BaseComicResponse
import com.naisuapps.marveldataverse.data.model.comics.Comic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CharacterService @Inject constructor(private val apiCharacterClient: CharacterApiClient) {
    suspend fun getCharacters(limit: Int, offset: Int): BaseCharacterResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<BaseCharacterResponse> =
                apiCharacterClient.getAllCharacters(limit, offset)
            response.body()
        }
    }

    suspend fun getCharacterComics(characterId: Int): List<Comic> {
        return withContext(Dispatchers.IO) {
            val response: Response<BaseComicResponse> =
                apiCharacterClient.getCharacterComics(characterId)
            response.body()?.data?.comics ?: emptyList()
        }
    }

    suspend fun getRandomCharacter(): List<Character> {
        return withContext(Dispatchers.IO) {
            val response: Response<BaseCharacterResponse> = apiCharacterClient.getRandomCharacter()
            response.body()?.data?.characters ?: emptyList()
        }
    }
}