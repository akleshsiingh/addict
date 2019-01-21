package com.example.healthnode.data.viewModel

import android.arch.lifecycle.MutableLiveData
import com.example.healthnode.data.base.BaseViewModel
import com.example.healthnode.data.dao.TargetDao
import com.example.healthnode.data.entity.Target
import com.example.healthnode.utils.Logger
import com.example.healthnode.utils.Resource
import com.example.healthnode.utils.toIoThread
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


}


