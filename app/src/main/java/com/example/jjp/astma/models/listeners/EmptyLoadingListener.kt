package com.example.jjp.astma.models.listeners

interface EmptyLoadingListener {
    fun onModelLoaded()

    fun onModelFailure(error: Throwable?)
}