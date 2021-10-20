package com.naisuapps.marveldataverse.data.network

import com.naisuapps.marveldataverse.data.model.BaseResponse
import com.naisuapps.marveldataverse.data.model.Character
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class CharacterService @Inject constructor(private val apiCharacterClient: CharacterApiClient) {
    suspend fun getCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            val response: Response<BaseResponse> = apiCharacterClient.getAllCharacters()
            response.body()?.data?.characters ?: emptyList()
        }
    }
}