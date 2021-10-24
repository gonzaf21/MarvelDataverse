package com.naisuapps.marveldataverse.data.network

import com.naisuapps.marveldataverse.data.model.characters.BaseCharacterResponse
import com.naisuapps.marveldataverse.data.model.comics.BaseComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<BaseCharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = 20//100
    ): Response<BaseComicResponse>

    @GET("characters")
    suspend fun getRandomCharacter(
        @Query("limit") limit: Int = 1,
        @Query("offset") offset: Int = (0 until 1459).random()
    ): Response<BaseCharacterResponse>
}