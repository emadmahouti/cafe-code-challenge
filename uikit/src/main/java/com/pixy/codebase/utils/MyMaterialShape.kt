package com.pixy.codebase.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

/**
 * Created by emadmahouti on 5/12/23
 */
class MyMaterialShape: MaterialShapeDrawable() {

    fun color(color: Int): MyMaterialShape {
        setTint(color)
        return this
    }

    fun border(color: Int, size: Int = 1.dpToPx): MyMaterialShape {
        setStroke(size.toFloat(), ColorStateList.valueOf(color))
        return this
    }

    @SuppressLint("RestrictedApi")
    fun shadow(color: Int = Color.LTGRAY, size: Int = 10.dpToPx): MyMaterialShape {
        setShadowColor(color)
        elevation = size.toFloat()
        shadowCompatibilityMode =

            SHADOW_COMPAT_MODE_ALWAYS
        try {
            shadowVerticalOffset = 0
        } catch (e: Exception) {
            //We don't need this
        }

        return this
    }

    fun corners(topLeft: Int, topRight: Int, bottomRight: Int, bottomLeft: Int): MyMaterialShape {
        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setTopLeftCorner(CornerFamily.ROUNDED, topLeft.toFloat())
            .setTopRightCorner(CornerFamily.ROUNDED, topRight.toFloat())
            .setBottomLeftCorner(CornerFamily.ROUNDED, bottomLeft.toFloat())
            .setBottomRightCorner(CornerFamily.ROUNDED, bottomRight.toFloat())

        this.shapeAppearanceModel = shapeAppearanceModel.build()

        return this
    }

    fun corners(corners: Int): MyMaterialShape {
        return corners(corners, corners, corners, corners)
    }
}
