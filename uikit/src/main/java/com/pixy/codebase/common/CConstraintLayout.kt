package com.pixy.codebase.common

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * Created by emadmahouti on 2/9/24
 */
open class CConstraintLayout(context: Context) : ConstraintLayout(context) {
    private val constraintsList = mutableListOf<ViewConstraintMargin>()



    override fun onViewAdded(view: View?) {
        view?.id = generateViewId()
        super.onViewAdded(view)
    }

    fun constraint(block: ConstraintSet.() -> Unit) {
        ConstraintSet().apply {
            val setConstraint: ConstraintSet = this
            clone(this@CConstraintLayout)
            block(this)
            constraintsList.forEach {
                setConstraint.connect(it.firstViewConstraint.viewId,it.firstViewConstraint.direction, it.secondViewConstraint.viewId, it.secondViewConstraint.direction, it.margin ?: 0)
            }
            applyTo(this@CConstraintLayout)
        }
    }

    private fun getDir(view: ViewConstraint): Int = view.direction

    infix fun ViewConstraint.connect(dir: Int) : ViewConstraintMargin {
        val constraint =  ViewConstraintMargin(ViewConstraint(this.viewId,direction), ViewConstraint(ConstraintSet.PARENT_ID,dir))
        constraintsList.add(constraint)
        return constraint
    }

    infix fun ViewConstraint.connect(view: ViewConstraint): ViewConstraintMargin {
        val constraint =  ViewConstraintMargin(this, view)
        constraintsList.add(constraint)
        return constraint
    }

    infix fun ViewConstraint.connectToParent(dir: ViewConstraint) : ViewConstraintMargin {
        val constraint =  ViewConstraintMargin(ViewConstraint(this.viewId,direction), ViewConstraint(ConstraintSet.PARENT_ID,dir.direction))
        constraintsList.add(constraint)
        return constraint
    }

    private fun removeConstraint(viewConstraintMargin: ViewConstraintMargin)
    {
        if(constraintsList.contains(viewConstraintMargin))
            constraintsList.remove(viewConstraintMargin)
    }

    infix fun ViewConstraintMargin.margin(value: Int) {

        removeConstraint(this)
        val constraint =  ViewConstraintMargin(firstViewConstraint, secondViewConstraint, value)
        constraintsList.add(constraint)
    }
}


data class ViewConstraint (val viewId : Int, val direction: Int)
data class ViewConstraintMargin (val firstViewConstraint: ViewConstraint,val secondViewConstraint: ViewConstraint , var margin : Int? = null)

val View.cLeft: ViewConstraint get() = ViewConstraint(this.id, ConstraintSet.LEFT)
val View.cRight: ViewConstraint get() = ViewConstraint(this.id, ConstraintSet.RIGHT)
val View.cTop: ViewConstraint get() = ViewConstraint(this.id, ConstraintSet.TOP)
val View.cBottom: ViewConstraint get() = ViewConstraint(this.id, ConstraintSet.BOTTOM)