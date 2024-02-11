package com.pixy.codebase.common

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import coil.load

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
        if(ratio != null)
        {
            if(widthBased)
            {
                val width = MeasureSpec.getSize(widthMeasureSpec)
                val height: Int = (width * ratio!!).toInt()

                super.onMeasure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
                )
            }
            else
            {
                val height = MeasureSpec.getSize(heightMeasureSpec)
                val width: Int = (height / ratio!!).toInt()

                super.onMeasure(
                    MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),heightMeasureSpec
                )
            }
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}