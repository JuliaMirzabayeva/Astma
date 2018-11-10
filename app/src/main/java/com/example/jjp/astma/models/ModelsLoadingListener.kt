package com.example.jjp.astma.models

interface ModelsLoadingListener<T> {
    fun onModelsLoaded(models: List<T>)

    fun onModelFailure(error: Throwable?)
}