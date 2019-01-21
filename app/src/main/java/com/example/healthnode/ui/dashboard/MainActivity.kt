package com.example.healthnode.ui.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.healthnode.R
import com.example.healthnode.data.viewModel.MyViewModelFactory
import com.example.healthnode.data.viewModel.TargetViewModel
import com.example.healthnode.ui.base.BaseActivity
import com.example.healthnode.utils.Resource
import com.example.healthnode.utils.longToast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import com.example.healthnode.utils.Logger


private const val TAG = "MAIN_ACTIVITY"

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: MyViewModelFactory

    // here i dont have to worry about making it SYNCRONISED under any scenerio
    val vm: TargetViewModel by lazy {
        ViewModelProviders.of(this, factory)[TargetViewModel::class.java]
    }

    override fun getContentView() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent?) {

        val adapterGrid = AdapterAddictionGrid { target ->
            vm.updateCurrentCount(target)
        }

        listAddiction.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                Logger.e(TAG, " h - ${listAddiction.height}")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    listAddiction.viewTreeObserver.removeOnGlobalLayoutListener(this)
                else listAddiction.viewTreeObserver.removeGlobalOnLayoutListener(this)

                adapterGrid.height = (listAddiction.height / 3f)
                listAddiction.apply {
                    layoutManager = GridLayoutManager(this.context, 2)
                    adapter = adapterGrid
                }
                vm.initTarget() // get list of addictions
            }
        })

        vm.allTargets.observe(this, Observer {
            it ?: return@Observer
            when {
                it.status == Resource.STATUS.SUCCESS -> {
                    adapterGrid.list = it.data!! // Here i'll make sure data not going to be null
                }
                it.status == Resource.STATUS.ERROR -> longToast(it.error)
                it.status == Resource.STATUS.LOADING -> {
                }
            }
        })

        //VERTICAL LINE
        val verticalDecoration = DividerItemDecoration(listAddiction.context, DividerItemDecoration.HORIZONTAL)
        val verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider)
        verticalDecoration.setDrawable(verticalDivider!!)
        listAddiction.addItemDecoration(verticalDecoration)

        //HORIZONTAL LINE
        val horizontalDecoration = DividerItemDecoration(listAddiction.context, DividerItemDecoration.VERTICAL)
        val horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider)
        horizontalDecoration.setDrawable(horizontalDivider!!)
        listAddiction.addItemDecoration(horizontalDecoration)
    }

    companion object {
        fun getNewIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.apply {
            add(0, 33, 2, "setting").setIcon(R.drawable.ic_setting).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
            add(0, 44, 1, "refresh").setIcon(R.drawable.ic_refresh).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item ?: return false
        when (item.itemId) {
            33 -> onBackPressed()
            44 -> vm.resetCurrentCounter()
        }
        return true
    }
}


