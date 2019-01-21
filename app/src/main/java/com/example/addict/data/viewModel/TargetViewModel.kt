package com.example.addict.data.viewModel

import android.arch.lifecycle.MutableLiveData
import com.example.addict.data.base.BaseViewModel
import com.example.addict.data.dao.TargetDao
import com.example.addict.data.entity.Target
import com.example.addict.utils.Logger
import com.example.addict.utils.Resource
import com.example.addict.utils.toIoThread
import io.reactivex.Single

class TargetViewModel(private val dao: TargetDao) : BaseViewModel() {

    val allTargets = MutableLiveData<Resource<List<Target>>>()

    fun initTarget() {
        addToCompositeDisposable(
            dao.selectAll()
                .toIoThread()
                .doOnSubscribe { allTargets.postValue(Resource.loading()) }
                .subscribe({
                    if (!it.isEmpty())
                        allTargets.postValue(Resource.success(it))
                    else insertAddictionList()

                }, { allTargets.postValue(Resource.error(it.localizedMessage)) })
        )
    }

    private fun insertAddictionList() {
        mutableListOf<Target>()
            .apply {
                add(Target(title = "A"))
                add(Target(title = "D"))
                add(Target(title = "D"))
                add(Target(title = "I"))
                add(Target(title = "C"))
                add(Target(title = "T"))
            }.also { dao.insertAll(it) }
    }


    fun updateData(list: List<Target>) {
        addToCompositeDisposable(Single.fromCallable {
            dao.insertAll(list as MutableList<Target>)
        }.toIoThread().subscribe({
            Logger.e("updated ", " $it")
        }, { it.printStackTrace() }))
    }

    fun updateCurrentCount(target: Target) {
        addToCompositeDisposable(
            Single.fromCallable {
                dao.updateTarget(target)
            }.toIoThread().subscribe({
                Logger.e("updated target", " $it")
            },{it.printStackTrace()})
        )
    }

    fun resetCurrentCounter() {
        addToCompositeDisposable(
            Single.fromCallable {
                dao.clearCurrentCounter()
            }.toIoThread().subscribe({
                Logger.e("updated target", " $it")
            },{it.printStackTrace()})
        )
    }


}


