package com.example.healthnode.utils

class Resource<out T>(val status: STATUS, val data: T?, val error: String = "") {

    enum class STATUS {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(payload: T): Resource<T> {
            return Resource(status = STATUS.SUCCESS, data = payload)
        }

        fun <T> error(error: String): Resource<T> {
            return Resource(status = STATUS.ERROR, data = null, error = error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = STATUS.LOADING, data = null)
        }

    }

}