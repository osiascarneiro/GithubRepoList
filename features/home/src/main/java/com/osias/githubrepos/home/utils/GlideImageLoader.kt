package com.osias.githubrepos.home.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.osias.githubrepos.core.utils.ImageLoader

class GlideImageLoader: ImageLoader {

    private var request: RequestManager? = null
    private var imageView: ImageView? = null

    override fun with(view: ImageView): ImageLoader {
        request = Glide.with(view)
        imageView = view
        return this
    }

    override fun load(url: String?, placeholder: Int) {
        imageView?.let {
            request?.load(url)
                   ?.placeholder(placeholder)
                   ?.circleCrop()
                   ?.into(it)
        }
    }
}