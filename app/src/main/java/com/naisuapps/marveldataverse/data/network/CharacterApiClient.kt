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
        @Query("limit") limit: Int = 100
    ): Response<BaseCharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = 100
    ): Response<BaseComicResponse>
}