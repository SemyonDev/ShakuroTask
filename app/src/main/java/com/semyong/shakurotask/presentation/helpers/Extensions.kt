package com.semyong.shakurotask.presentation.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun Context.showToastLong(errorMessage:String) =
    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

fun ImageView.load(url: String) =
    Glide.with(this)
        .load(url)
        .into(this)