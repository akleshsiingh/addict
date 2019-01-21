package com.example.healthnode.ui.target

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.healthnode.R
import com.example.healthnode.data.entity.Target
import com.example.healthnode.data.viewModel.MyViewModelFactory
import com.example.healthnode.data.viewModel.TargetViewModel
import com.example.healthnode.ui.base.BaseActivity
import com.example.healthnode.ui.dashboard.MainActivity
import com.example.healthnode.utils.Logger
import com.example.healthnode.utils.Resource
import com.example.healthnode.utils.click
import com.example.healthnode.utils.longToast
import kotlinx.android.synthetic.main.activity_target.*
import javax.inject.Inject

class TargetActivity : BaseActivity() {

    @Inject
    lateinit var factory: MyViewModelFactory

    override fun getContentView() = R.layout.activity_target

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent?) {


        val vm = ViewModelProviders.of(this, factory)[TargetViewModel::class.java]
        vm.initTarget()

        val adapterAddict = AdapterAddict()
        listAddictions.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = adapterAddict
        }

        vm.allTargets.observe(this, Observer {
            it ?: return@Observer
            when {
                it.status == Resource.STATUS.SUCCESS -> {
                    adapterAddict.list = it.data!! // Here i ll make sure data not going to be null
                }
                it.status == Resource.STATUS.ERROR -> longToast(it.error)
                it.status == Resource.STATUS.LOADING -> { }
            }
        })

        btnSave.click { vm.updateData(adapterAddict.list); startActivity(MainActivity.getNewIntent(this)) }
    }


}
