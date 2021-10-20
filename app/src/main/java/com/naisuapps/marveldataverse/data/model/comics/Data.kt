package com.naisuapps.marveldataverse.data.model.comics

import com.google.gson.annotations.SerializedName

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results")
    val comics: List<Comic>,
    val total: Int
)