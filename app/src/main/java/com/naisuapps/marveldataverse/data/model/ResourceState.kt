package com.naisuapps.marveldataverse.data.model

sealed class ResourceState {
    data class Success<T>(val data: T): ResourceState()
    data class Error(val msg: String): ResourceState()
    object Loading: ResourceState()
    object InitialState: ResourceState()
}