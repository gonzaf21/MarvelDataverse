package com.naisuapps.marveldataverse.data.model.enums

enum class Scope {
    PRODUCTION,
    DEVELOPMENT,
    LOCAL;

    companion object {
        /**
         * Returns api base url with scope passed by parameter.
         */
        fun getApiBaseUrlByScope(currentScope: Scope): String {
            return when (currentScope) {
                PRODUCTION -> "https://gateway.marvel.com/v1/public/"
                DEVELOPMENT -> "https://gateway.marvel.com/v1/public/"
                LOCAL -> "https://gateway.marvel.com/v1/public/"
            }
        }
    }
}