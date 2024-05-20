package com.dexciuq.arbuz_kz.utils.image_loader

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, imageView: ImageView)
}