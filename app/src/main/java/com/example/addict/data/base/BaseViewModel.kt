package com.example.addict.data.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private var cd = CompositeDisposable()

    open fun addToCompositeDisposable(d: Disposable) {
        if (cd.isDisposed)
            cd = CompositeDisposable()
        cd.add(d)

    }


    override fun onCleared() {
        if (!cd.isDisposed)
            cd.dispose()
        super.onCleared()
    }
}