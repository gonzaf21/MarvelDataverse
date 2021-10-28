package com.naisuapps.marveldataverse.data.model

sealed class ResourceState<out T> {
    data class Success<T>(val data: T): ResourceState<T>()
    data class Error(val msg: String): ResourceState<Nothing>()
    object Loading: ResourceState<Nothing>()
    object InitialState: ResourceState<Nothing>()
}