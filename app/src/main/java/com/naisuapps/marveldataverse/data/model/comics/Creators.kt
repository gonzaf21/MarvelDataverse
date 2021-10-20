package com.naisuapps.marveldataverse.data.model.comics

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)