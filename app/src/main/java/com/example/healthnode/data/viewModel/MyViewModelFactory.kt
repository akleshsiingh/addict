package com.example.healthnode.data.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.healthnode.data.dao.TargetDao
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MyViewModelFactory @Inject constructor(private val daoTargetDao: TargetDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TargetViewModel(daoTargetDao) as T
    }
}