package com.osias.githubrepos.core.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {
    fun with(view: ImageView): ImageLoader
    fun load(url: String?, @DrawableRes placeholder: Int = 0)
}