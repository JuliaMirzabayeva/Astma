package com.example.jjp.astma.models.listeners

interface ModelsLoadingListener<T> {
    fun onModelsLoaded(models: List<T>)

    fun onModelFailure(error: Throwable?)
}