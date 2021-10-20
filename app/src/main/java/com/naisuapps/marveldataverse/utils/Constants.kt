package com.naisuapps.marveldataverse.utils

import com.naisuapps.marveldataverse.data.model.enums.Scope
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    val currentScope: Scope = Scope.PRODUCTION
    const val API_KEY = "33d8b23e4cb922b46cc7357fa40822da"
    const val PRIVATE_API_KEY = "850f1a5d85a8659fae6aa1afbdcaffc5b3e855ee"

    val ts = Timestamp(System.currentTimeMillis()).time.toString()

    fun hash(): String {
        val input = "$ts$PRIVATE_API_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}