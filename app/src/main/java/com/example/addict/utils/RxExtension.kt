package com.example.addict.utils

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.toIoThread(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Single<T>.toIoThread(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}