package com.naisuapps.marveldataverse.data.model.comics

data class Thumbnail(
    val extension: String,
    val path: String
){
    override fun toString(): String {
        return "${path.replace("http", "https")}.$extension" // Coil just does not work with http normal requests.
    }
}