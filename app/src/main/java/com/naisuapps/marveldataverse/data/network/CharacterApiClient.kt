package com.naisuapps.marveldataverse.data.network

import com.naisuapps.marveldataverse.data.model.BaseResponse
import com.naisuapps.marveldataverse.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiClient {
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int = 100
    ): Response<BaseResponse>
}