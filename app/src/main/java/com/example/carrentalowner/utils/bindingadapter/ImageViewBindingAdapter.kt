package com.example.carrentalowner.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.carrentalowner.utils.extension.loadImageView


class ImageViewBindingAdapter {
    companion object{
        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView : ImageView, imageUrl : String){
            imageView.loadImageView(imageUrl)
        }
    }
}