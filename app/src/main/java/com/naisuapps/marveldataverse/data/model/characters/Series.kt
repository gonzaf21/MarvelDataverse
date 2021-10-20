package com.naisuapps.marveldataverse.data.model.characters

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)