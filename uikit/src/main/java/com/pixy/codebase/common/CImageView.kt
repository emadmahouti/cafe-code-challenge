package com.pixy.codebase.common

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.pixy.codebase.R

/**
 * Created by emadmahouti on 5/12/23
 */
open class CImageView(context: Context): AppCompatImageView(context) {
    private var ratio : Float? = null
    private var widthBased = true

    constructor(context: Context,ratio : Float,widthBased : Boolean = true) : this(context)
    {
        this.widthBased = widthBased
        this.ratio = ratio
    }

    fun set(res: Int) {
        setBackgroundResource(res)
    }

    fun set(url: String, placeholder: Int) {
        load(url) {
            placeholder(placeholder)
            error(placeholder)
            crossfade(true)
        }
    }

    fun clipImage() {
        clipToOutline = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(ratio != null) {
            val nonNullRatio: Float = ratio!!

            if(widthBased) {
                val height = (widthMeasureSpec * nonNullRatio).toInt()
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
            } else {
                val width = (heightMeasureSpec * nonNullRatio).toInt()
                super.onMeasure( MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), heightMeasureSpec)
            }

            return
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}