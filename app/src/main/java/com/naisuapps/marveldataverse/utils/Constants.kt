package com.naisuapps.marveldataverse.utils

import com.naisuapps.marveldataverse.BuildConfig
import com.naisuapps.marveldataverse.data.model.enums.Scope
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    val currentScope: Scope = Scope.PRODUCTION
    val ts = Timestamp(System.currentTimeMillis()).time.toString()

    /**
     * Creates md5 hash from api keys and timestamp needed for api calls.
     */
    fun hash(): String {
        val input = "$ts${BuildConfig.PRIVATE_API_KEY}${BuildConfig.API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}