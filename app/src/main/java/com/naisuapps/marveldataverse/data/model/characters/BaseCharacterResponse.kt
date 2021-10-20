package com.naisuapps.marveldataverse.data.model.characters

data class BaseCharacterResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)