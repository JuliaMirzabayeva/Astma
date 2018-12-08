package com.example.jjp.astma.models.listeners

interface ModelLoadingListener<T> {
    fun onModelLoaded(model: T)

    fun onModelError(error : String?)

    fun onModelFailure(error: Throwable?)
}