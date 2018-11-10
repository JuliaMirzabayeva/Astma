package com.example.jjp.astma.models

interface ModelLoadingListener<T> {
    fun onModelLoaded(model: T)

    fun onModelFailure(error: Throwable?)
}