package com.pixy.codebase.providers

import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 5/12/23
 */

class ParamsProvider {
    companion object {
        const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
        const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
    }
    class Linear : LinearLayout.LayoutParams {
        constructor(width: Int, height: Int) : super(width, height)
        constructor(width: Int, height: Int, weight: Float) : super(width, height, weight)

        fun gravity(gravity: Int): Linear {
            this.gravity = gravity
            return this
        }

        companion object {
            fun defaultParams() = Linear(MATCH, WRAP)
            fun wrapContent() = Linear(WRAP, WRAP)
            fun availableHeightParams() = Linear(MATCH, 0, 1f)
            fun availableHeightParams(weight: Float) = Linear(MATCH, 0, weight)
            fun availableWidthParams() = Linear(0, WRAP, 1f)
            fun availableWidthParams(weight: Float) = Linear(0, WRAP, weight)
            fun justOccupyWidth() = Linear(0, 1, 1f)
            fun justOccupyWidth(height: Int) = Linear(0, height, 1f)
            fun justOccupyHeight() = Linear(1, 0, 1f)
            fun horizontalLine() = Linear(MATCH, 1.dpToPx)
            fun verticalLine() = Linear(1.dpToPx, MATCH)

            fun get(w: Int, h: Int): Linear {
                return Linear(w, h)
            }

            fun get(w: Int, h: Int, weight: Float): Linear {
                return Linear(w, h, weight)
            }


        }
    }

    class Constraint : ConstraintLayout.LayoutParams {
        constructor(width: Int, height: Int) : super(width, height)
        companion object {

            fun availableWidthParams() = Linear(0, WRAP)
            fun availableHeightParams() = Linear(MATCH, 0)
            fun defaultParams() = Constraint(MATCH, WRAP)
            fun wrapContent() = Constraint(WRAP, WRAP)

            fun get(w: Int, h: Int): Constraint {
                return Constraint(w, h)
            }
        }
    }


    class Frame : FrameLayout.LayoutParams {
        constructor(width: Int, height: Int) : super(width, height)

        fun gravity(gravity: Int): Frame {
            this.gravity = gravity
            return this
        }

        companion object {
            fun defaultParams() = Frame(MATCH, WRAP)
            fun fullSize() = Frame(MATCH, MATCH)
            fun wrapContent() = Frame(WRAP, WRAP)
            fun get(w: Int, h: Int): Frame {
                return Frame(w, h)
            }
        }
    }
}

fun MarginLayoutParams.margins(value: Int): MarginLayoutParams {
    setMargins(value, value, value, value)

    return this
}

fun MarginLayoutParams.margin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): MarginLayoutParams {
    setMargins(left, top, right, bottom)

    return this
}
