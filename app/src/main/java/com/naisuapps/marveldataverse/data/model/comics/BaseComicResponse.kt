package com.naisuapps.marveldataverse.data.model.comics

data class BaseComicResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)