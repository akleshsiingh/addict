package com.example.healthnode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.fitness.data.BleDevice
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.BleScanCallback
import com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_DELTA
import java.util.Arrays.asList
import com.google.android.gms.tasks.Task
import java.util.*


private const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnScan.click {
            startScanning()
        }
    }

    private fun startScanning() {
        GoogleSignIn.getLastSignedInAccount(this)?.also {
            val response = Fitness.getBleClient(
                this, it
            ).startBleScan(Arrays.asList(DataType.TYPE_STEP_COUNT_DELTA), 1000, callback)
        }

    }

    val callback = object : BleScanCallback() {
        override fun onDeviceFound(device: BleDevice) {
            // A device that provides the requested data types is available
            Logger.e(TAG, " FOUND $device")
        }

        override fun onScanStopped() {
            // The scan timed out or was interrupted
            Logger.e(TAG, " SCANNING STOPPED")
        }
    }
}

private fun View.click(function: () -> Unit) {
    this.setOnClickListener { function.invoke() }
}
