package com.huntams.data.base

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getString
import com.google.android.material.snackbar.Snackbar
import com.huntams.data.R

fun toastError(block: () -> Unit, view: View,context : Context)  {
    val mySnackbar = Snackbar.make(
        view,
        getString(context,R.string.internet_connection),
        Snackbar.LENGTH_INDEFINITE
    )
    mySnackbar.setAction(getString(context,R.string.reload)) {
        block()
    }
    mySnackbar.show()
}