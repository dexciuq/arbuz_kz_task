package com.dexciuq.arbuz_kz.utils.image_loader

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoader @Inject constructor(
    private val glide: Glide
) : ImageLoader {
    override fun load(url: String, imageView: ImageView) {
        glide.requestManagerRetriever
            .get(imageView)
            .load(url)
            .into(imageView)
    }
}