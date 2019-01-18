package com.example.healthnode.utils

import android.view.View

 fun View.click(function: () -> Unit) {
    this.setOnClickListener { function.invoke() }
}