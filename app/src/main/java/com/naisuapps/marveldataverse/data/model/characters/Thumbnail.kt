package com.naisuapps.marveldataverse.data.model.characters

data class Thumbnail(
    val extension: String,
    val path: String,
    var thumbnailUri: String = "$path.$extension"
) {
    override fun toString(): String {
        return "${path.replace("http", "https")}.$extension" // Coil just does not work with http normal requests.
    }
}