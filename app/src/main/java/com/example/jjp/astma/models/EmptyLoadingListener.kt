package com.example.jjp.astma.models

interface EmptyLoadingListener {
    fun onModelLoaded()

    fun onModelFailure(error: Throwable?)
}