package com.osias.githubrepos.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.osias.githubrepos.core.utils.ImageLoader

fun ImageView.load(url: String?, imageLoader: ImageLoader, @DrawableRes placeholder: Int = 0) {
    post {
        imageLoader.with(this).load(url, placeholder)
    }
}